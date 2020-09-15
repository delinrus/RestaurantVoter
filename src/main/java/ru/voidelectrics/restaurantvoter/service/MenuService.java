package ru.voidelectrics.restaurantvoter.service;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.voidelectrics.restaurantvoter.model.Menu;
import ru.voidelectrics.restaurantvoter.repository.MenuItemRepository;
import ru.voidelectrics.restaurantvoter.repository.MenuRepository;
import ru.voidelectrics.restaurantvoter.repository.RestaurantRepository;
import ru.voidelectrics.restaurantvoter.to.MenuTo;
import ru.voidelectrics.restaurantvoter.util.ToConversionUtil;

import java.time.Clock;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static ru.voidelectrics.restaurantvoter.util.ToConversionUtil.convert;

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

    public List<MenuTo> getAll() {
        return menuRepository.findAll().stream().map(ToConversionUtil::convert).collect(Collectors.toList());
    }

    public Menu getByDateAndRestaurantId(LocalDate date, long restaurantId) {
        return menuRepository.getByDateAndRestaurantId(date, restaurantId);
    }

    public MenuTo getForToday(Long restaurantId) {
        return convert(menuRepository.getByDateAndRestaurantId(LocalDate.now(clock), restaurantId));
    }

    @CacheEvict(value = "restaurantTos", allEntries = true)
    @Transactional
    public MenuTo saveForToday(MenuTo menuTo) {
        Menu menu = convert(menuTo);
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
        return convert(result);
    }
}
