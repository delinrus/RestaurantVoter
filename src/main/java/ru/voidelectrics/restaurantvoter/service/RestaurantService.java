package ru.voidelectrics.restaurantvoter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.voidelectrics.restaurantvoter.model.Restaurant;
import ru.voidelectrics.restaurantvoter.repository.MenuRepository;
import ru.voidelectrics.restaurantvoter.repository.RestaurantRepository;
import ru.voidelectrics.restaurantvoter.repository.VoteRepository;
import ru.voidelectrics.restaurantvoter.to.RestaurantTo;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantService {
    private final RestaurantRepository repository;

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private VoteRepository voteRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.repository = restaurantRepository;
    }

    public List<RestaurantTo> getAll() {
        LocalDate today = LocalDate.of(2020, 8, 22); // TODO: get today date

        return repository.findAll().stream()
                .map(r -> new RestaurantTo(
                        r.id(),
                        r.getName(),
                        menuRepository.getByDateAndRestaurantId(today, r.id()),
                        voteRepository.countByDateAndRestaurantId(today, r.id())))
                .collect(Collectors.toList());
    }

    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    public Restaurant get(Long id) {
        return repository.getOne(id);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public Restaurant update(Restaurant restaurant, long id) {
        restaurant.setId(id);
        return repository.save(restaurant);
    }
}
