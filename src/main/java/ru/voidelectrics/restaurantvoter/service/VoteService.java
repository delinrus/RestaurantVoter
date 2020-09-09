package ru.voidelectrics.restaurantvoter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.voidelectrics.restaurantvoter.model.Vote;
import ru.voidelectrics.restaurantvoter.repository.RestaurantRepository;
import ru.voidelectrics.restaurantvoter.repository.UserRepository;
import ru.voidelectrics.restaurantvoter.repository.VoteRepository;
import ru.voidelectrics.restaurantvoter.util.exeption.RequestForbidden;

import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    private Clock clock;

    public VoteService(VoteRepository voteRepository,
                       UserRepository userRepository,
                       RestaurantRepository restaurantRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public List<Vote> getAll(long userId) {
        return voteRepository.getAll(userId);
    }

    @Transactional
    public Vote save(long restaurantId, long userId) {
        if (isTooLateForChangingVote()) {
            throw new RequestForbidden(RequestForbidden.FORBIDDEN_TIME_MSG);
        }
        LocalDate today = LocalDate.now(clock);
        Vote vote = new Vote();
        vote.setUser(userRepository.getOne(userId));
        vote.setDate(today);
        vote.setRestaurant(restaurantRepository.getOne(restaurantId));
        Vote previousVote = voteRepository.getByDateAndUserId(today, userId);
        if (previousVote != null) {
            vote.setId(previousVote.getId());
        }
        return voteRepository.save(vote);
    }

    public long countTodayVotes(long restaurantId) {
        return voteRepository.countByDateAndRestaurantId(LocalDate.now(clock), restaurantId);
    }

    public Vote getByDateAndUserId(LocalDate date, long userId) {
        return voteRepository.getByDateAndUserId(date, userId);
    }

    private boolean isTooLateForChangingVote() {
        return LocalTime.now(clock).compareTo(LocalTime.of(11, 0)) > 0;
    }
}
