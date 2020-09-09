package ru.voidelectrics.restaurantvoter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.voidelectrics.restaurantvoter.model.Menu;
import ru.voidelectrics.restaurantvoter.model.MenuItem;
import ru.voidelectrics.restaurantvoter.repository.MenuItemRepository;
import ru.voidelectrics.restaurantvoter.repository.MenuRepository;
import ru.voidelectrics.restaurantvoter.to.MenuTo;
import ru.voidelectrics.restaurantvoter.util.ToConversionUtil;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;

@Service
public class MenuService {
    @Autowired
    private final MenuRepository repository;

    @Autowired
    Clock clock;

    @Autowired
    MenuItemRepository menuItemRepository;

    public MenuService(MenuRepository repository) {
        this.repository = repository;
    }

    public List<Menu> getAll() {
        return repository.findAll();
    }

    public Menu getByDateAndRestaurantId(LocalDate date, long restaurantId) {
        return repository.getByDateAndRestaurantId(date, restaurantId);
    }

    public Menu getForToday(Long restaurantId) {
        return repository.getByDateAndRestaurantId(LocalDate.now(clock), restaurantId);
    }

    @CacheEvict(value = "restaurantTos", allEntries = true)
    @Transactional
    public Menu saveForToday(MenuTo menuTo) {
        Menu menu = ToConversionUtil.convert(menuTo);
        LocalDate today = LocalDate.now(clock);
        menu.setId(null);
        Menu previous = repository.getByDateAndRestaurantId(today, menu.getRestaurant().getId());
        if (previous != null) {
            /// repository.delete(previous);
            repository.delete(previous.id());
        }
        menu.setDate(today);
        Menu result = repository.save(menu);
        for (MenuItem item : menu.getMenuItems()) {
            item.setMenu(menu);
            menuItemRepository.save(item);
        }
        return result;
    }
}
