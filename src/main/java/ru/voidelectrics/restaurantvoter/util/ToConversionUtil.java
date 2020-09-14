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
        Menu menu = new Menu();
        Restaurant restaurant = new Restaurant();
        restaurant.setId(menuTo.getRestaurantId());
        menu.setRestaurant(restaurant);
        List<MenuItem> menuItems = menuTo.getMenuItemTos().stream()
                .map(to -> new MenuItem(null, to.getName(), to.getPrice()))
                .collect(Collectors.toList());
        menu.setMenuItems(menuItems);
        return menu;
    }

    public static VoteTo convert(Vote vote) {
        VoteTo voteTo = new VoteTo();
        voteTo.setId(vote.getId());
        voteTo.setRestaurantId(vote.getRestaurant().getId());
        return voteTo;
    }

    public static MenuItemTo convert(MenuItem menuItem) {
        MenuItemTo menuItemTo = new MenuItemTo();
        menuItemTo.setName(menuItem.getName());
        menuItemTo.setPrice(menuItem.getPrice());
        return menuItemTo;
    }
}
