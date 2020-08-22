package ru.voidelectrics.restaurantvoter.web;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.voidelectrics.restaurantvoter.model.Restaurant;
import ru.voidelectrics.restaurantvoter.service.RestaurantService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {

    private final RestaurantService service;

    public RestaurantController(RestaurantService service) {
        this.service = service;
    }

    @GetMapping
    public List<Restaurant> getAll() {
        return service.getAll();
    }

}
