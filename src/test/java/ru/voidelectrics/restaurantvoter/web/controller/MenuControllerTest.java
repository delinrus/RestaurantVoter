package ru.voidelectrics.restaurantvoter.web.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.voidelectrics.restaurantvoter.MenuTestData;
import ru.voidelectrics.restaurantvoter.model.Menu;
import ru.voidelectrics.restaurantvoter.service.MenuService;
import ru.voidelectrics.restaurantvoter.web.AbstractControllerTest;
import ru.voidelectrics.restaurantvoter.web.json.JsonUtil;

import java.time.Instant;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.voidelectrics.restaurantvoter.MenuTestData.MENU1;
import static ru.voidelectrics.restaurantvoter.MenuTestData.MENU_MATCHER;
import static ru.voidelectrics.restaurantvoter.RestaurantTestData.RESTAURANT1_ID;
import static ru.voidelectrics.restaurantvoter.TestUtil.readFromJson;
import static ru.voidelectrics.restaurantvoter.TestUtil.userHttpBasic;
import static ru.voidelectrics.restaurantvoter.UserTestData.USER;

class MenuControllerTest extends AbstractControllerTest {

    @Autowired
    private MenuService menuService;

    @BeforeEach
    void setUp() {
        clockMock().setInstant(Instant.parse("2020-08-22T09:15:30Z"));
    }

    @Test
    void getForToday() throws Exception {
        perform(MockMvcRequestBuilders.get("/rest/menus" + "/today" + "?restaurantId=" + RESTAURANT1_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(MENU_MATCHER.contentJson(MENU1));
    }

    @Test
    void saveForToday() throws Exception {
        Menu newMenu = MenuTestData.getNew();

        ResultActions action = perform(MockMvcRequestBuilders.post("/rest/menus" + "/today")
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(USER))
                .content(JsonUtil.writeValue(newMenu)))
                .andDo(print())
                .andExpect(status().isOk());

        Menu created = readFromJson(action, Menu.class);
        long newId = created.getId();
        newMenu.setId(newId);
        newMenu.setDate(LocalDate.now(clockMock()));
        long restaurantId = newMenu.getRestaurant().getId();

        assertThat(created).usingRecursiveComparison()
                .ignoringFields("menuItems.id", "menuItems.menu")
                .isEqualTo(newMenu);

        assertThat(menuService.getForToday(restaurantId)).usingRecursiveComparison()
                .ignoringFields("menuItems.id", "menuItems.menu")
                .isEqualTo(newMenu);
    }
}