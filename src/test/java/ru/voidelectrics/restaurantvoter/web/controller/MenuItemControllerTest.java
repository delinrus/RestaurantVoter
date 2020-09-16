package ru.voidelectrics.restaurantvoter.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.voidelectrics.restaurantvoter.util.ToConversionUtil;

import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.voidelectrics.restaurantvoter.MenuTestData.*;
import static ru.voidelectrics.restaurantvoter.TestUtil.userHttpBasic;
import static ru.voidelectrics.restaurantvoter.UserTestData.ADMIN;

class MenuItemControllerTest extends AbstractControllerTest {
    private static final String REST_URL = MenuItemController.REST_URL + '/';

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MENU_ITEM_TO_MATCHER.contentJson(MENU_ITEMS.stream().map(ToConversionUtil::convert).collect(Collectors.toList())));
    }

    @Test
    void getByRestaurantId() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "?restaurantId=100000")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(MENU_ITEM_TO_MATCHER.contentJson(RESTAURANT1_MENU_ITEMS.stream().map(ToConversionUtil::convert).collect(Collectors.toList())));

    }
}