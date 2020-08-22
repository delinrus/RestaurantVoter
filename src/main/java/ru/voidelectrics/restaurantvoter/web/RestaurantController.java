package ru.voidelectrics.restaurantvoter.web;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.voidelectrics.restaurantvoter.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/rest/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {

    public RestaurantController() {

    }

    @GetMapping
    public List<Restaurant> getAll() {
        List<Restaurant> list = new ArrayList<>();
        list.add(new Restaurant("Continental"));
        list.add(new Restaurant("Voshod"));

        return list;
    }

}
