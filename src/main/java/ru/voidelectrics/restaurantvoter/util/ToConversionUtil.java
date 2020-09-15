package ru.voidelectrics.restaurantvoter.util;

import ru.voidelectrics.restaurantvoter.model.Menu;
import ru.voidelectrics.restaurantvoter.model.MenuItem;
import ru.voidelectrics.restaurantvoter.model.Restaurant;
import ru.voidelectrics.restaurantvoter.model.Vote;
import ru.voidelectrics.restaurantvoter.to.MenuItemTo;
import ru.voidelectrics.restaurantvoter.to.MenuTo;
import ru.voidelectrics.restaurantvoter.to.VoteTo;

import java.util.List;
import java.util.stream.Collectors;

public class ToConversionUtil {
    public static Menu convert(MenuTo menuTo) {
        if (menuTo == null) {
            return null;
        }
        Menu menu = new Menu();
        Restaurant restaurant = new Restaurant();
        restaurant.setId(menuTo.getRestaurantId());
        menu.setRestaurant(restaurant);
        if (menuTo.getMenuItemTos() == null) {
            menu.setMenuItems(null);
        } else {
            List<MenuItem> menuItems = menuTo.getMenuItemTos().stream()
                    .map(to -> new MenuItem(null, to.getName(), to.getPrice()))
                    .collect(Collectors.toList());
            menu.setMenuItems(menuItems);
        }
        return menu;
    }

    public static MenuTo convert(Menu menu) {
        if (menu == null) {
            return null;
        }
        MenuTo menuTo = new MenuTo();
        menuTo.setRestaurantId(menu.getRestaurant().getId());
        if (menu.getMenuItems() == null) {
            menuTo.setMenuItemTos(null);
        } else {
            List<MenuItemTo> menuItemTos = menu.getMenuItems().stream()
                    .map(ToConversionUtil::convert)
                    .collect(Collectors.toList());
            menuTo.setMenuItemTos(menuItemTos);
        }
        return menuTo;
    }

    public static VoteTo convert(Vote vote) {
        if (vote == null) {
            return null;
        }
        VoteTo voteTo = new VoteTo();
        voteTo.setId(vote.getId());
        voteTo.setRestaurantId(vote.getRestaurant().getId());
        return voteTo;
    }

    public static MenuItemTo convert(MenuItem menuItem) {
        if (menuItem == null) {
            return null;
        }
        MenuItemTo menuItemTo = new MenuItemTo();
        menuItemTo.setName(menuItem.getName());
        menuItemTo.setPrice(menuItem.getPrice());
        return menuItemTo;
    }
}
