package ru.voidelectrics.restaurantvoter.util;

import ru.voidelectrics.restaurantvoter.model.Menu;
import ru.voidelectrics.restaurantvoter.model.MenuItem;
import ru.voidelectrics.restaurantvoter.to.MenuTo;

import java.util.List;
import java.util.stream.Collectors;

public class ToConversionUtil {
    public static Menu convert(MenuTo menuTo) {
        Menu menu = new Menu();
        menu.setRestaurant(menuTo.getRestaurant());
        List<MenuItem> menuItems = menuTo.getMenuItemTos().stream()
        .map(to -> new MenuItem(null, to.getName(), to.getPrice()))
        .collect(Collectors.toList());
        menu.setMenuItems(menuItems);
        return menu;
    }
}
