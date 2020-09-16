package ru.voidelectrics.restaurantvoter;

import ru.voidelectrics.restaurantvoter.model.Menu;
import ru.voidelectrics.restaurantvoter.model.MenuItem;
import ru.voidelectrics.restaurantvoter.to.MenuItemTo;
import ru.voidelectrics.restaurantvoter.to.MenuTo;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.voidelectrics.restaurantvoter.RestaurantTestData.*;
import static ru.voidelectrics.restaurantvoter.RestaurantTestData.RESTAURANT1_ID;
import static ru.voidelectrics.restaurantvoter.util.ToConversionUtil.convert;

public class MenuTestData {
    public static TestMatcher<Menu> MENU_MATCHER = TestMatcher.usingAssertions(Menu.class,
            (a, e) -> assertThat(a).usingRecursiveComparison()
                    .ignoringFields("restaurant").ignoringAllOverriddenEquals().isEqualTo(e),
            (a, e) -> {
                throw new UnsupportedOperationException();
            });

    public static TestMatcher<MenuTo> MENU_TO_MATCHER = TestMatcher.usingEqualsAssertions(MenuTo.class);

    public static TestMatcher<MenuItem> MENU_ITEM_MATCHER = TestMatcher.usingFieldsWithIgnoringAssertions(MenuItem.class, "menus");
    public static TestMatcher<MenuItemTo> MENU_ITEM_TO_MATCHER = TestMatcher.usingEqualsAssertions(MenuItemTo.class);

    public static final MenuItem MENU_ITEM1 = new MenuItem(100008L, RESTAURANT1_ID, "Божественная яишенка", 1000);
    public static final MenuItem MENU_ITEM2 = new MenuItem(100009L, RESTAURANT1_ID, "Блинчики", 500);
    public static final MenuItem MENU_ITEM3 = new MenuItem(100010L, RESTAURANT1_ID, "Бутерброд с сёмгой", 900);
    public static final MenuItem MENU_ITEM4 = new MenuItem(100011L, RESTAURANT1_ID, "Омлет", 1500);
    public static final MenuItem MENU_ITEM5 = new MenuItem(100012L, RESTAURANT1_ID, "Эко лаваш из шпината", 1200);
    public static final MenuItem MENU_ITEM6 = new MenuItem(100013L, RESTAURANT1_ID, "Манная каша с вишневым вареньем", 300);
    public static final MenuItem MENU_ITEM7 = new MenuItem(100014L,  RESTAURANT2.id(), "Пицца с ветчиной и овощами", 1000);
    public static final MenuItem MENU_ITEM8 = new MenuItem(100015L,  RESTAURANT2.id(), "Пицца Карбонара", 1200);
    public static final MenuItem MENU_ITEM9 = new MenuItem(100016L,  RESTAURANT2.id(), "Хачапури", 900);
    public static final MenuItem MENU_ITEM10 = new MenuItem(100017L, RESTAURANT2.id(),  "Мега-пицца", 10000);
    public static final MenuItem MENU_ITEM11 = new MenuItem(100018L, RESTAURANT2.id(),  "Сырники с малиновым вареньем", 290);
    public static final MenuItem MENU_ITEM12 = new MenuItem(100019L, RESTAURANT2.id(),  "Яблочный пирог", 700);
    public static final MenuItem MENU_ITEM13 = new MenuItem(100020L, RESTAURANT2.id(),  "Морс ягодный", 200);
    public static final MenuItem MENU_ITEM14 = new MenuItem(100021L, RESTAURANT2.id(),  "Пирожки с хреном", 300);
    public static final MenuItem MENU_ITEM15 = new MenuItem(100022L, RESTAURANT2.id(),  "Божественный свиток", 999999);
    public static final MenuItem NEW_MENU_ITEM1 = new MenuItem(null, null, "Макароны с сыром", 690);
    public static final MenuItem NEW_MENU_ITEM2 = new MenuItem(null, null, "Салат Брут", 1250);

    public static final List<MenuItem> MENU_ITEMS = List.of(MENU_ITEM1, MENU_ITEM2, MENU_ITEM3, MENU_ITEM4, MENU_ITEM5, MENU_ITEM6, MENU_ITEM7, MENU_ITEM8,
            MENU_ITEM9, MENU_ITEM10, MENU_ITEM11, MENU_ITEM12, MENU_ITEM13, MENU_ITEM14, MENU_ITEM15);

    public static final List<MenuItem> RESTAURANT1_MENU_ITEMS = List.of(MENU_ITEM1, MENU_ITEM2, MENU_ITEM3, MENU_ITEM4, MENU_ITEM5, MENU_ITEM6);

    public static final Menu MENU1 = new Menu(100002L, RESTAURANT1, LocalDate.parse("2020-08-22"), MENU_ITEM1, MENU_ITEM2, MENU_ITEM3);
    public static final Menu MENU2 = new Menu(100003L, RESTAURANT1, LocalDate.parse("2020-08-21"), MENU_ITEM4, MENU_ITEM5, MENU_ITEM6);
    public static final Menu MENU3 = new Menu(100004L, RESTAURANT2, LocalDate.parse("2020-08-22"), MENU_ITEM7, MENU_ITEM8, MENU_ITEM9);
    public static final Menu MENU4 = new Menu(100005L, RESTAURANT2, LocalDate.parse("2020-08-21"), MENU_ITEM10, MENU_ITEM11, MENU_ITEM12, MENU_ITEM13, MENU_ITEM14, MENU_ITEM15);

    public static Menu getNew() {
        return new Menu(null, RESTAURANT2, null, NEW_MENU_ITEM1, NEW_MENU_ITEM2);
    }

    public static MenuTo getNewMenuTo() {
        return new MenuTo(RESTAURANT2.id(), convert(NEW_MENU_ITEM1), convert(NEW_MENU_ITEM2));
    }

    public static MenuItem getNewMenuItem() {
        return new MenuItem(null, null, "Салат Брут", 1250);
    }

    public static final MenuTo MENU_TO_CONTAINING_OLD_ITEMS = new MenuTo(RESTAURANT1.id(), convert(MENU_ITEM2), convert(NEW_MENU_ITEM2));
}
