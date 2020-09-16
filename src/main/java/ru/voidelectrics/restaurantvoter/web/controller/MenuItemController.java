package ru.voidelectrics.restaurantvoter.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.voidelectrics.restaurantvoter.model.MenuItem;
import ru.voidelectrics.restaurantvoter.repository.MenuItemRepository;
import ru.voidelectrics.restaurantvoter.to.MenuItemTo;
import ru.voidelectrics.restaurantvoter.util.ToConversionUtil;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = MenuItemController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuItemController {
    static final String REST_URL = "/rest/admin/menuItems";

    @Autowired
    private MenuItemRepository repository;


    @GetMapping
    public List<MenuItemTo> getAll(@RequestParam(value = "restaurantId", required = false)  Long restaurantId) {
        List<MenuItem> menuItems = restaurantId == null ?  repository.findAll() : repository.getByRestaurantId(restaurantId);
        return menuItems.stream().map(ToConversionUtil::convert).collect(Collectors.toList());
    }
}
