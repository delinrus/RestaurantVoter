package ru.voidelectrics.restaurantvoter.web;

import ru.voidelectrics.restaurantvoter.service.RestaurantService;

public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }
}
