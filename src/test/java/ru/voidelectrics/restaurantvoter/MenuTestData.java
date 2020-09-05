package ru.voidelectrics.restaurantvoter;

import ru.voidelectrics.restaurantvoter.model.Menu;
import ru.voidelectrics.restaurantvoter.model.MenuItem;

import java.time.LocalDate;

import static ru.voidelectrics.restaurantvoter.RestaurantTestData.RESTAURANT1;
import static ru.voidelectrics.restaurantvoter.RestaurantTestData.RESTAURANT2;

public class MenuTestData {
    public static final MenuItem MENU_ITEM1 = new MenuItem(100008L, "Божественная яишенка", 1000);
    public static final MenuItem MENU_ITEM2 = new MenuItem(100009L, "Блинчики", 500);
    public static final MenuItem MENU_ITEM3 = new MenuItem(100010L, "Бутерброд с сёмгой", 900);
    public static final MenuItem MENU_ITEM4 = new MenuItem(100011L, "Омлет", 1500);
    public static final MenuItem MENU_ITEM5 = new MenuItem(100012L, "Эко лаваш из шпината", 1200);
    public static final MenuItem MENU_ITEM6 = new MenuItem(100013L, "Манная каша с вишневым вареньем", 300);
    public static final MenuItem MENU_ITEM7 = new MenuItem(100014L, "Пицца с ветчиной и овощами", 1000);
    public static final MenuItem MENU_ITEM8 = new MenuItem(100015L, "Пицца Карбонара", 1200);
    public static final MenuItem MENU_ITEM9 = new MenuItem(100016L, "Хачапури", 900);
    public static final MenuItem MENU_ITEM10 = new MenuItem(100017L, "Мега-пицца", 10000);
    public static final MenuItem MENU_ITEM11 = new MenuItem(100018L, "Сырники с малиновым вареньем", 290);
    public static final MenuItem MENU_ITEM12 = new MenuItem(100019L, "Яблочный пирог", 700);
    public static final MenuItem MENU_ITEM13 = new MenuItem(100020L, "Морс ягодный", 200);
    public static final MenuItem MENU_ITEM14 = new MenuItem(100021L, "Пирожки с хреном", 300);
    public static final MenuItem MENU_ITEM15 = new MenuItem(100022L, "Божественный свиток", 999999);

    public static final Menu MENU1 = new Menu(100002L, RESTAURANT1, LocalDate.parse("2020-08-22"), MENU_ITEM1, MENU_ITEM2, MENU_ITEM3);
    public static final Menu MENU2 = new Menu(100003L, RESTAURANT1, LocalDate.parse("2020-08-21"), MENU_ITEM4, MENU_ITEM5, MENU_ITEM6);
    public static final Menu MENU3 = new Menu(100004L, RESTAURANT2, LocalDate.parse("2020-08-22"), MENU_ITEM7, MENU_ITEM8, MENU_ITEM9);
    public static final Menu MENU4 = new Menu(100005L, RESTAURANT2, LocalDate.parse("2020-08-21"), MENU_ITEM10, MENU_ITEM11, MENU_ITEM12, MENU_ITEM13, MENU_ITEM14, MENU_ITEM15);
}
