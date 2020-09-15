package ru.voidelectrics.restaurantvoter.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.voidelectrics.restaurantvoter.model.Menu;
import ru.voidelectrics.restaurantvoter.model.Restaurant;
import ru.voidelectrics.restaurantvoter.repository.MenuRepository;
import ru.voidelectrics.restaurantvoter.repository.RestaurantRepository;
import ru.voidelectrics.restaurantvoter.to.RestaurantTo;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static ru.voidelectrics.restaurantvoter.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {
    private final RestaurantRepository repository;

    private final MenuRepository menuRepository;

    private final Clock clock;


    public RestaurantService(RestaurantRepository restaurantRepository,
                             MenuRepository menuRepository,
                             Clock clock) {
        this.repository = restaurantRepository;
        this.menuRepository = menuRepository;
        this.clock = clock;
    }

    @Cacheable("restaurantTos")
    @Transactional
    public List<RestaurantTo> getAll() {
        LocalDate today = LocalDate.now(clock);
        List<Menu> menusOfTheDay = menuRepository.getByDate(today);

        return repository.findAll().stream()
                .map(r -> new RestaurantTo(
                        r.id(),
                        r.getName(),
                        menusOfTheDay.stream()
                                .filter(menu -> menu.getRestaurant().getId() == r.id())
                                .findFirst()
                                .orElse(null)))
                .collect(Collectors.toList());
    }

    @CacheEvict(value = "restaurantTos", allEntries = true)
    public Restaurant create(Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }

    public Restaurant get(Long id) {
        return checkNotFoundWithId(repository.findById(id).orElse(null), id);
    }

    @CacheEvict(value = "restaurantTos", allEntries = true)
    public void delete(long id) {
        repository.deleteById(id);
    }

    @CacheEvict(value = "restaurantTos", allEntries = true)
    public Restaurant update(Restaurant restaurant, long id) {
        restaurant.setId(id);
        return repository.save(restaurant);
    }
}
