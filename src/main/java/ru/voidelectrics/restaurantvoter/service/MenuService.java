package ru.voidelectrics.restaurantvoter.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.voidelectrics.restaurantvoter.model.Menu;
import ru.voidelectrics.restaurantvoter.model.MenuItem;
import ru.voidelectrics.restaurantvoter.repository.MenuItemRepository;
import ru.voidelectrics.restaurantvoter.repository.MenuRepository;
import ru.voidelectrics.restaurantvoter.repository.RestaurantRepository;
import ru.voidelectrics.restaurantvoter.to.MenuTo;
import ru.voidelectrics.restaurantvoter.util.ToConversionUtil;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;

@Service
public class MenuService {
    private final Clock clock;

    private final MenuRepository menuRepository;

    private final MenuItemRepository menuItemRepository;

    private final RestaurantRepository restaurantRepository;

    public MenuService(Clock clock, MenuRepository repository, MenuItemRepository menuItemRepository, RestaurantRepository restaurantRepository) {
        this.clock = clock;
        this.menuRepository = repository;
        this.menuItemRepository = menuItemRepository;
        this.restaurantRepository = restaurantRepository;
    }

    public List<Menu> getAll() {
        return menuRepository.findAll();
    }

    public Menu getByDateAndRestaurantId(LocalDate date, long restaurantId) {
        return menuRepository.getByDateAndRestaurantId(date, restaurantId);
    }

    public Menu getForToday(Long restaurantId) {
        return menuRepository.getByDateAndRestaurantId(LocalDate.now(clock), restaurantId);
    }

    @CacheEvict(value = "restaurantTos", allEntries = true)
    @Transactional
    public Menu saveForToday(MenuTo menuTo) {
        Menu menu = ToConversionUtil.convert(menuTo);
        LocalDate today = LocalDate.now(clock);
        menu.setRestaurant(restaurantRepository.findById(menuTo.getRestaurantId()).orElse(null));
        menu.setId(null);
        Menu previous = menuRepository.getByDateAndRestaurantId(today, menu.getRestaurant().getId());
        if (previous != null) {
            /// repository.delete(previous);
            menuRepository.delete(previous.id());
        }
        menu.setDate(today);
        Menu result = menuRepository.save(menu);
        menu.getMenuItems().forEach(menuItem -> menuItem.setMenu(menu));
        menuItemRepository.saveAll(menu.getMenuItems());
        return result;
    }
}
