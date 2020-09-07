package ru.voidelectrics.restaurantvoter.service;

import org.springframework.stereotype.Service;
import ru.voidelectrics.restaurantvoter.model.Vote;
import ru.voidelectrics.restaurantvoter.repository.UserRepository;
import ru.voidelectrics.restaurantvoter.repository.VoteRepository;
import ru.voidelectrics.restaurantvoter.util.exeption.RequestForbidden;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;

    public VoteService(VoteRepository voteRepository, UserRepository userRepository) {
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
    }

    public List<Vote> getAll(long userId) {
        return voteRepository.getAll(userId);
    }

    public Vote save(Vote vote, long userId) {
        if (isTooLateForChangingVote()) {
            throw new RequestForbidden(RequestForbidden.FORBIDDEN_TIME_MSG);
        }
        vote.setUser(userRepository.getOne(userId));
        LocalDate today = LocalDate.now();
        vote.setDate(today);
        //voteRepository.delete();
        return voteRepository.save(vote);
    }

    public Vote getByDateAndUserId(LocalDate date, long userId) {
        return voteRepository.getByDateAndUserId(date, userId);
    }

    //public static final Vote VOTE2 = new Vote(100024L, RESTAURANT2, LocalDate.parse("2020-08-21"));

    private static boolean isTooLateForChangingVote() {
        return LocalTime.now().compareTo(LocalTime.of(18, 0)) > 0;
    }
}
