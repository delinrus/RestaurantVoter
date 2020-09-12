package ru.voidelectrics.restaurantvoter.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.voidelectrics.restaurantvoter.TimeMockingTest;
import ru.voidelectrics.restaurantvoter.model.Vote;
import ru.voidelectrics.restaurantvoter.util.exeption.RequestForbidden;

import java.time.Instant;
import java.time.LocalDate;

import static ru.voidelectrics.restaurantvoter.RestaurantTestData.RESTAURANT1_ID;
import static ru.voidelectrics.restaurantvoter.TestUtil.validateRootCause;
import static ru.voidelectrics.restaurantvoter.UserTestData.ADMIN_ID;
import static ru.voidelectrics.restaurantvoter.UserTestData.USER_ID;
import static ru.voidelectrics.restaurantvoter.VoteTestData.*;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
class VoteServiceTest extends TimeMockingTest {

    @Autowired
    private VoteService service;

    @Test
    void getByDateAndUserId() throws Exception {
        Vote vote = service.getByDateAndUserId(LocalDate.parse("2020-08-22"), ADMIN_ID);
        VOTE_MATCHER.assertMatch(vote, VOTE3);
    }

    @Test
    void changeExistingVote() throws Exception{
        clockMock().setInstant(Instant.parse("2020-08-22T09:15:30Z"));
        Vote created = service.save(RESTAURANT1_ID, USER_ID);
        long newId = created.id();
        Vote newVote = getNew();
        newVote.setId(newId);
        newVote.setDate(LocalDate.parse("2020-08-22"));
        VOTE_MATCHER.assertMatch(service.getByDateAndUserId(LocalDate.parse("2020-08-22"), USER_ID), newVote);
    }

    @Test
    void changeExistingVoteTooLate() throws Exception{
        clockMock().setInstant(Instant.parse("2020-08-22T11:02:00Z"));
        validateRootCause(() -> service.save(RESTAURANT1_ID, USER_ID), RequestForbidden.class);
    }

    @Test
    void firstVoteAfterNoChangeTime() throws Exception{
        clockMock().setInstant(Instant.parse("2020-08-23T11:02:00Z"));
        Vote created = service.save(RESTAURANT1_ID, USER_ID);
        long newId = created.id();
        Vote newVote = getNew();
        newVote.setId(newId);
        newVote.setDate(LocalDate.parse("2020-08-23"));
        VOTE_MATCHER.assertMatch(service.getByDateAndUserId(LocalDate.parse("2020-08-23"), USER_ID), newVote);
    }

    @Test
    void createNewVote() throws Exception{
        clockMock().setInstant(Instant.parse("2020-08-23T09:00:00Z"));
        Vote created = service.save(RESTAURANT1_ID, USER_ID);
        long newId = created.id();
        Vote newVote = getNew();
        newVote.setId(newId);
        newVote.setDate(LocalDate.parse("2020-08-23"));
        VOTE_MATCHER.assertMatch(service.getByDateAndUserId(LocalDate.parse("2020-08-23"), USER_ID), newVote);
    }
}