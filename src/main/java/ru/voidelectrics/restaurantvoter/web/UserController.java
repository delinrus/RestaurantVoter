package ru.voidelectrics.restaurantvoter.web;

import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.voidelectrics.restaurantvoter.model.User;
import ru.voidelectrics.restaurantvoter.service.UserService;
import ru.voidelectrics.restaurantvoter.web.controller.RestaurantController;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(value = "/rest/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private static final Logger log = getLogger(RestaurantController.class);

    private final UserService service;

    public UserController(UserService userService) {
        this.service = userService;
    }

    @GetMapping
    public List<User> getAll() {
        log.debug("getAllRestaurants");
        return service.getAll();
    }
}
