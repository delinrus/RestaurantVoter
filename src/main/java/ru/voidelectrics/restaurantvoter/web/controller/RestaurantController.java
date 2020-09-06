package ru.voidelectrics.restaurantvoter.web.controller;

import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.voidelectrics.restaurantvoter.model.Restaurant;
import ru.voidelectrics.restaurantvoter.service.RestaurantService;
import ru.voidelectrics.restaurantvoter.to.RestaurantTo;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(value = "/rest", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {
    private static final Logger log = getLogger(RestaurantController.class);

    private final RestaurantService service;

    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping(value = "/restaurants")
    public List<RestaurantTo> getAll() {
        log.debug("getAllRestaurants");
        return service.getAll();
    }

    @PostMapping(value = "/admin/restaurants", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant create(@RequestBody Restaurant restaurant) {
        log.debug("createRestaurant");
        return service.create(restaurant);
    }
}
