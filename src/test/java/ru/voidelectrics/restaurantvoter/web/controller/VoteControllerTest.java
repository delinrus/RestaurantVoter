package ru.voidelectrics.restaurantvoter.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.voidelectrics.restaurantvoter.model.Vote;
import ru.voidelectrics.restaurantvoter.service.VoteService;
import ru.voidelectrics.restaurantvoter.to.VoteTo;
import ru.voidelectrics.restaurantvoter.web.json.JsonUtil;

import java.time.Instant;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.voidelectrics.restaurantvoter.RestaurantTestData.RESTAURANT1;
import static ru.voidelectrics.restaurantvoter.RestaurantTestData.RESTAURANT2;
import static ru.voidelectrics.restaurantvoter.TestUtil.readFromJson;
import static ru.voidelectrics.restaurantvoter.TestUtil.userHttpBasic;
import static ru.voidelectrics.restaurantvoter.UserTestData.USER;
import static ru.voidelectrics.restaurantvoter.UserTestData.USER_ID;
import static ru.voidelectrics.restaurantvoter.VoteTestData.*;

class VoteControllerTest extends AbstractControllerTest {
    private static final String REST_URL = VoteController.REST_URL + '/';

    @Autowired
    private VoteService service;

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(VOTE_MATCHER.contentJson(VOTE1, VOTE2));
    }

    @Test
    void create() throws Exception {
        clockMock().setInstant(Instant.parse("2020-08-23T09:00:00Z"));

        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(new VoteTo(RESTAURANT1.id())))
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isCreated());

        VoteTo created = readFromJson(action, VoteTo.class);
        Vote newVote = new Vote();
        long newId = created.id();
        newVote.setId(newId);
        newVote.setRestaurantId(RESTAURANT1.getId());
        newVote.setDate(LocalDate.parse("2020-08-23"));

        assertEquals(created.getRestaurantId(), RESTAURANT1.id());
        VOTE_MATCHER.assertMatch(service.getByDateAndUserId(LocalDate.parse("2020-08-23"), USER_ID), newVote);
    }

    @Test
    void update() throws Exception {
        clockMock().setInstant(Instant.parse("2020-08-22T09:00:00Z"));

        perform(MockMvcRequestBuilders.put(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(new VoteTo(RESTAURANT2.id())))
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isNoContent());

        Vote created = service.getByDateAndUserId(LocalDate.parse("2020-08-22"), USER_ID);
        Vote newVote = new Vote();
        long newId = created.id();
        newVote.setId(newId);
        newVote.setRestaurantId(RESTAURANT2.id());
        newVote.setDate(LocalDate.parse("2020-08-22"));
        VOTE_MATCHER.assertMatch(created, newVote);
    }
}