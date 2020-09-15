package ru.voidelectrics.restaurantvoter.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.voidelectrics.restaurantvoter.model.Vote;
import ru.voidelectrics.restaurantvoter.repository.UserRepository;
import ru.voidelectrics.restaurantvoter.repository.VoteRepository;
import ru.voidelectrics.restaurantvoter.util.exeption.IllegalRequestDataException;
import ru.voidelectrics.restaurantvoter.util.exeption.RequestForbidden;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.function.Consumer;

@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final Clock clock;


    public VoteService(VoteRepository voteRepository,
                       UserRepository userRepository,
                       Clock clock) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.clock = clock;
    }

    public List<Vote> getAll(long userId) {
        return voteRepository.getAll(userId);
    }

    private Vote saveHelper(long restaurantId, long userId, Consumer<Vote> exitChecks) {
        LocalDate today = LocalDate.now(clock);
        Vote vote = new Vote();
        Vote previousVote = voteRepository.getByDateAndUserId(today, userId);
        exitChecks.accept(previousVote); // Checking if operation is permitted, throwing exception otherwise
        vote.setUser(userRepository.getOne(userId));
        vote.setDate(today);
        vote.setRestaurantId(restaurantId);
        if (previousVote != null) {
            vote.setId(previousVote.getId());
        }
        return vote;
    }

    @Transactional
    @CacheEvict(value = "voteCounts", allEntries = true)
    public void update(long restaurantId, long userId) {
        Vote vote = saveHelper(restaurantId, userId, (previousVote) -> {
            if (previousVote == null) {
                throw new IllegalRequestDataException("No vote to update");
            }
            if (isTooLateForChangingVote()) {
                throw new RequestForbidden(RequestForbidden.FORBIDDEN_TIME_MSG);
            }
        });
        voteRepository.save(vote);
    }

    @Transactional
    @CacheEvict(value = "voteCounts", allEntries = true)
    public Vote create(long restaurantId, long userId) {
        Vote vote = saveHelper(restaurantId, userId, (previousVote) -> {
            if (previousVote != null) {
                throw new IllegalRequestDataException("Vote already exists");
            }
        });
        return voteRepository.save(vote);
    }

    @Cacheable("voteCounts")
    public long countVotes(long restaurantId, LocalDate date) {
        return voteRepository.countByDateAndRestaurantId(date, restaurantId);
    }

    public Vote getByDateAndUserId(LocalDate date, long userId) {
        return voteRepository.getByDateAndUserId(date, userId);
    }

    private boolean isTooLateForChangingVote() {
        return LocalTime.now(clock).compareTo(LocalTime.of(11, 0)) > 0;
    }
}