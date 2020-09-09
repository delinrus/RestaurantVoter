package ru.voidelectrics.restaurantvoter.web.controller;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.voidelectrics.restaurantvoter.model.Restaurant;
import ru.voidelectrics.restaurantvoter.service.RestaurantService;
import ru.voidelectrics.restaurantvoter.service.VoteService;
import ru.voidelectrics.restaurantvoter.to.RestaurantTo;

import javax.validation.Valid;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.voidelectrics.restaurantvoter.util.ValidationUtil.assureIdConsistent;
import static ru.voidelectrics.restaurantvoter.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {
    private static final Logger log = getLogger(RestaurantController.class);

    public static final String REST_ALL_URL = "/rest/restaurants";
    public static final String REST_ADMIN_URL = "/rest/admin/restaurants";

    private final RestaurantService restaurantService;
    private final VoteService voteService;

    public RestaurantController(RestaurantService service, VoteService voteService) {
        this.restaurantService = service;
        this.voteService = voteService;
    }

    @GetMapping(value = REST_ALL_URL)
    public List<RestaurantTo> getAll() {
        log.debug("getAllRestaurants");
        return restaurantService.getAll();
    }

    @PostMapping(value = REST_ADMIN_URL, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant create(@Valid @RequestBody Restaurant restaurant) {
        log.debug("createRestaurant");
        checkNew(restaurant);
        return restaurantService.create(restaurant);
    }

    @DeleteMapping(value = REST_ADMIN_URL + "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        log.debug("deleteRestaurant");
        restaurantService.delete(id);
    }

    @PutMapping(value = REST_ADMIN_URL + "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant update(@Valid @RequestBody Restaurant restaurant, @PathVariable long id) {
        log.debug("updateRestaurant");
        assureIdConsistent(restaurant, id);
        return restaurantService.update(restaurant, id);
    }

    @GetMapping(value = REST_ALL_URL + "/{id}/votecount")
    public long countVotes(@PathVariable long id) {
        log.debug("getAllRestaurants");
        return voteService.countTodayVotes(id);
    }

}
