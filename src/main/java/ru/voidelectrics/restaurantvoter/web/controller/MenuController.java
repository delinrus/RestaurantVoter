package ru.voidelectrics.restaurantvoter.web.controller;

import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.voidelectrics.restaurantvoter.model.Menu;
import ru.voidelectrics.restaurantvoter.service.MenuService;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(value = "/rest/menus", produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuController {
    private static final Logger log = getLogger(RestaurantController.class);

    private final MenuService service;

    public MenuController(MenuService service) {
        this.service = service;
    }

    @GetMapping
    public List<Menu> getAll() {
        return service.getAll();
    }

    @GetMapping(value = "/today")
    public Menu getForToday(@RequestParam(value = "restaurantId") Long restaurantId) {
        return service.getForToday(restaurantId);
    }

    @PostMapping(value = "/today")
    public Menu saveForToday(@RequestBody Menu menu) {
        return service.saveForToday(menu);
    }
}
