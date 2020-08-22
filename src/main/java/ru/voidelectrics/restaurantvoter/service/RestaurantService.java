package ru.voidelectrics.restaurantvoter.service;

import org.springframework.stereotype.Service;
import ru.voidelectrics.restaurantvoter.model.Restaurant;
import ru.voidelectrics.restaurantvoter.repository.RestaurantRepository;

import java.util.List;

@Service
public class RestaurantService {
    private final RestaurantRepository repository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.repository = restaurantRepository;
    }

    public List<Restaurant> getAll() {
        return repository.findAll();
    }
}
