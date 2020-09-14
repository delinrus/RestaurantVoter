package ru.voidelectrics.restaurantvoter;

import ru.voidelectrics.restaurantvoter.model.Vote;

import java.time.LocalDate;

import static ru.voidelectrics.restaurantvoter.RestaurantTestData.RESTAURANT1;
import static ru.voidelectrics.restaurantvoter.RestaurantTestData.RESTAURANT2;

public class VoteTestData {
    public static TestMatcher<Vote> VOTE_MATCHER = TestMatcher.usingFieldsWithIgnoringAssertions(Vote.class, "user");

    public static final long VOTE1_ID = 100023L;

    public static final Vote VOTE1 = new Vote(VOTE1_ID, RESTAURANT1, LocalDate.parse("2020-08-22"));  // for user user@mail.ru
    public static final Vote VOTE2 = new Vote(VOTE1_ID + 1, RESTAURANT2, LocalDate.parse("2020-08-21"));  // for user user@mail.ru
    public static final Vote VOTE3 = new Vote(VOTE1_ID + 2, RESTAURANT1, LocalDate.parse("2020-08-22"));  // for user admin@mail.ru
    public static final Vote VOTE4 = new Vote(VOTE1_ID + 3, RESTAURANT1, LocalDate.parse("2020-08-20"));  // for user admin@mail.ru


    public static Vote getNew() {
        return new Vote(null, RESTAURANT1, LocalDate.parse("2020-08-22"));
    }

    public static Vote getUpdated() {
        return new Vote(VOTE1_ID, RESTAURANT2, LocalDate.parse("2020-08-22"));
    }
}
