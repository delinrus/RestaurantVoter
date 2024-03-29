package ru.voidelectrics.restaurantvoter.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.voidelectrics.restaurantvoter.TestMatcher;
import ru.voidelectrics.restaurantvoter.model.Restaurant;
import ru.voidelectrics.restaurantvoter.service.RestaurantService;
import ru.voidelectrics.restaurantvoter.util.exeption.NotFoundException;
import ru.voidelectrics.restaurantvoter.web.json.JsonUtil;

import java.time.Instant;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.voidelectrics.restaurantvoter.RestaurantTestData.*;
import static ru.voidelectrics.restaurantvoter.TestUtil.*;
import static ru.voidelectrics.restaurantvoter.UserTestData.ADMIN;

class RestaurantControllerTest extends AbstractControllerTest {

    private final static TestMatcher<Long> LONG_MATCHER = TestMatcher.usingEqualsAssertions(Long.class);

    @Autowired
    private RestaurantService restaurantService;

    @Test
    void getAll() throws Exception {
        clockMock().setInstant(Instant.parse("2020-08-22T09:15:30Z"));
        perform(MockMvcRequestBuilders.get(RestaurantController.REST_ALL_URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_TO_MATCHER.contentJson(RESTAURANTS_TOS));
    }

    @Test
    void create() throws Exception {
        Restaurant newRestaurant = getNew();

        ResultActions action = perform(MockMvcRequestBuilders.post(RestaurantController.REST_ADMIN_URL)
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newRestaurant)));

        Restaurant created = readFromJson(action, Restaurant.class);
        long newId = created.id();
        newRestaurant.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRestaurant);
        RESTAURANT_MATCHER.assertMatch(restaurantService.get(newId), newRestaurant);
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(RestaurantController.REST_ADMIN_URL + '/' + RESTAURANT1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());

        validateRootCause(() -> restaurantService.get(RESTAURANT1_ID), NotFoundException.class);
    }


    @Test
    void update() throws Exception {
        perform(MockMvcRequestBuilders.delete(RestaurantController.REST_ADMIN_URL + '/' + RESTAURANT1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());

        validateRootCause(() -> restaurantService.get(RESTAURANT1_ID), NotFoundException.class);
    }

    @Test
    void createInvalid() throws Exception {
        Restaurant newRestaurant = getNew();
        newRestaurant.setName("x");

        ResultActions action = perform(MockMvcRequestBuilders.post(RestaurantController.REST_ADMIN_URL)
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newRestaurant)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void countVotes() throws Exception {
        clockMock().setInstant(Instant.parse("2020-08-22T17:15:30Z"));
        perform(MockMvcRequestBuilders.get(RestaurantController.REST_ALL_URL + '/' + RESTAURANT1_ID + "/votecount?date=2020-08-22"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(LONG_MATCHER.contentJson(new Long(2)));
    }
}