package ru.voidelectrics.restaurantvoter.service;

import ru.voidelectrics.restaurantvoter.repository.RestaurantRepository;

public class RestaurantService {

    private final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }
}
