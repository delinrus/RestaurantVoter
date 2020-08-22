package ru.voidelectrics.restaurantvoter.web;

import org.slf4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.voidelectrics.restaurantvoter.model.Restaurant;
import ru.voidelectrics.restaurantvoter.service.RestaurantService;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(value = "/rest/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {
    private static final Logger log = getLogger(RestaurantController.class);

    private final RestaurantService service;

    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping
    public List<Restaurant> getAll() {
        log.debug("getAllRestaurants");
        return service.getAll();
    }

}
