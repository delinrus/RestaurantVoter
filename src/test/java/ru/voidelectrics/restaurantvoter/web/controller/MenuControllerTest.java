package ru.voidelectrics.restaurantvoter.web.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.voidelectrics.restaurantvoter.model.Menu;
import ru.voidelectrics.restaurantvoter.model.MenuItem;
import ru.voidelectrics.restaurantvoter.repository.MenuItemRepository;
import ru.voidelectrics.restaurantvoter.service.MenuService;
import ru.voidelectrics.restaurantvoter.to.MenuTo;
import ru.voidelectrics.restaurantvoter.web.json.JsonUtil;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.voidelectrics.restaurantvoter.MenuTestData.*;
import static ru.voidelectrics.restaurantvoter.RestaurantTestData.*;
import static ru.voidelectrics.restaurantvoter.TestUtil.readFromJson;
import static ru.voidelectrics.restaurantvoter.TestUtil.userHttpBasic;
import static ru.voidelectrics.restaurantvoter.UserTestData.ADMIN;
import static ru.voidelectrics.restaurantvoter.util.ToConversionUtil.convert;

class MenuControllerTest extends AbstractControllerTest {

    private static final String REST_URL = MenuController.REST_URL + '/';

    @Autowired
    private MenuService menuService;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @BeforeEach
    void setUp() {
        clockMock().setInstant(Instant.parse("2020-08-22T09:15:30Z"));
    }

    @Test
    void getForToday() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "/today" + "?restaurantId=" + RESTAURANT1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MENU_TO_MATCHER.contentJson(convert(MENU1)));
    }

    @Test
    void saveForToday() throws Exception {
        ResultActions action = perform(MockMvcRequestBuilders.put(REST_URL + "/today")
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(getNewMenuTo())))
                .andDo(print())
                .andExpect(status().isOk());

        MenuTo created = readFromJson(action, MenuTo.class);
        MenuTo newMenu = getNewMenuTo();
        long restaurantId = newMenu.getRestaurantId();

        assertThat(created).usingRecursiveComparison()
                .ignoringFields("menuItemTos.id")
                .isEqualTo(newMenu);

        assertThat(menuService.getForToday(restaurantId)).usingRecursiveComparison()
                .ignoringFields("menuItemTos.id")
                .isEqualTo(newMenu);
    }

    @Test
    void saveForTodayWithSomeOldMenuItems() throws Exception {
        ResultActions action = perform(MockMvcRequestBuilders.put(REST_URL + "/today")
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(MENU_TO_CONTAINING_OLD_ITEMS)))
                .andDo(print())
                .andExpect(status().isOk());

        List<MenuItem> all = menuItemRepository.findAll();
        all.forEach(System.out::println);


        MenuItem newMenuItem = getNewMenuItem();
        newMenuItem.setId(100027L);
        newMenuItem.setRestaurantId(RESTAURANT1.id());

        MENU_ITEM_MATCHER.assertMatch(all, MENU_ITEM2,  MENU_ITEM4, MENU_ITEM5, MENU_ITEM6, MENU_ITEM7, MENU_ITEM8, MENU_ITEM9,
                MENU_ITEM10, MENU_ITEM11, MENU_ITEM12, MENU_ITEM13, MENU_ITEM14, MENU_ITEM15, newMenuItem);

        MenuTo menuForToday = menuService.getForToday(RESTAURANT1.id());
        MENU_TO_MATCHER.assertMatch(menuForToday, new MenuTo(RESTAURANT1.id(), convert(newMenuItem), convert(MENU_ITEM2)));
    }
}