package ru.voidelectrics.restaurantvoter.web.controller;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.voidelectrics.restaurantvoter.model.Restaurant;
import ru.voidelectrics.restaurantvoter.service.RestaurantService;
import ru.voidelectrics.restaurantvoter.to.RestaurantTo;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {
    private static final Logger log = getLogger(RestaurantController.class);

    public static final String REST_ALL_URL = "/rest/restaurants";
    public static final String REST_ADMIN_URL = "/rest/admin/restaurants";

    private final RestaurantService service;

    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping(value = REST_ALL_URL)
    public List<RestaurantTo> getAll() {
        log.debug("getAllRestaurants");
        return service.getAll();
    }

    @PostMapping(value = REST_ADMIN_URL, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant create(@RequestBody Restaurant restaurant) {
        log.debug("createRestaurant");
        return service.create(restaurant);
    }

    @DeleteMapping(value = REST_ADMIN_URL + "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        log.debug("deleteRestaurant");
        service.delete(id);
    }

}
