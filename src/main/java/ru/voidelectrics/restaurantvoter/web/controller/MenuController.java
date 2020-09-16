package ru.voidelectrics.restaurantvoter.web.controller;

import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.voidelectrics.restaurantvoter.service.MenuService;
import ru.voidelectrics.restaurantvoter.to.MenuTo;

import javax.validation.Valid;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(value = MenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuController {
    private static final Logger log = getLogger(RestaurantController.class);

    static final String REST_URL = "/rest/admin/menus";

    private final MenuService service;

    public MenuController(MenuService service) {
        this.service = service;
    }

    @GetMapping
    public List<MenuTo> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/today")
    public MenuTo getForToday(@RequestParam(value = "restaurantId") Long restaurantId) {
        return service.getForToday(restaurantId);
    }

    @PutMapping(value = "/today")
    public MenuTo saveForToday(@Valid @RequestBody MenuTo menuTo) {
        return service.saveForToday(menuTo);
    }
}
