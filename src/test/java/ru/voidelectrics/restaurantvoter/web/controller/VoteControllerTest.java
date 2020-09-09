package ru.voidelectrics.restaurantvoter.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.voidelectrics.restaurantvoter.service.VoteService;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.voidelectrics.restaurantvoter.TestUtil.userHttpBasic;
import static ru.voidelectrics.restaurantvoter.UserTestData.USER;
import static ru.voidelectrics.restaurantvoter.VoteTestData.*;

class VoteControllerTest extends AbstractControllerTest {
    private static final String REST_URL = VoteController.REST_URL+ '/';

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

}