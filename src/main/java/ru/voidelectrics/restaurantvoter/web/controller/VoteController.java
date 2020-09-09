package ru.voidelectrics.restaurantvoter.web.controller;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.voidelectrics.restaurantvoter.model.Vote;
import ru.voidelectrics.restaurantvoter.service.VoteService;
import ru.voidelectrics.restaurantvoter.to.VoteTo;
import ru.voidelectrics.restaurantvoter.web.SecurityUtil;

import javax.validation.Valid;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(value = VoteController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {
    private static final Logger log = getLogger(RestaurantController.class);

    static final String REST_URL = "/rest/votes";

    private final VoteService service;

    public VoteController(VoteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Vote> getAll() {
        log.debug("getAllRestaurants");
        long userId = SecurityUtil.authUserId();
        return service.getAll(userId);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void save(@Valid @RequestBody VoteTo voteTo) {
        long userId = SecurityUtil.authUserId();
        service.save(voteTo.getRestaurantId(), userId);
    }
}
