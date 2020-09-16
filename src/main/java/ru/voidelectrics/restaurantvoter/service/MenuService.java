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
import ru.voidelectrics.restaurantvoter.util.exeption.IllegalRequestDataException;

import java.time.Clock;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    private void checkMenuItem(MenuItem expected, MenuItem actual) {
        if (!expected.getId().equals(actual.getId()) ||
                !expected.getName().equals(actual.getName()) ||
                expected.getPrice() != actual.getPrice()) {
            throw new IllegalRequestDataException("MenuItem not equal to persisted one");
        }
    }

    @CacheEvict(value = "restaurantTos", allEntries = true)
    @Transactional
    public MenuTo saveForToday(MenuTo menuTo) {
        Menu menu = convert(menuTo);
        LocalDate today = LocalDate.now(clock);
        Long restaurantId = menuTo.getRestaurantId();

        // Checking if persisted items equal to items with id in received MenuTo
        Map<Long, MenuItem> itemsWithId = new HashMap<>();
        for (MenuItem mi : menu.getMenuItems()) {
            if (mi.getId() == null) {
                continue;
            }
            if (itemsWithId.containsKey(mi.getId())) {
                throw new IllegalRequestDataException("Menu contains duplicate items");
            }
            itemsWithId.put(mi.getId(), mi);
        }
        List<MenuItem> persistentItems = menuItemRepository.findAllById(itemsWithId.keySet()).stream()
                .filter(mi -> mi.getRestaurantId().equals(restaurantId))
                .collect(Collectors.toList());
        if (persistentItems.size() != itemsWithId.size()) {
            throw new IllegalRequestDataException("Menu items are not related to existing ones");
        }
        persistentItems.forEach(persistedItem -> checkMenuItem(persistedItem, itemsWithId.get(persistedItem.getId())));


        menu.setRestaurant(restaurantRepository.findById(restaurantId).orElse(null));
        menu.setId(null);

        // Removing previous Menu and MenuItems related only to this menu
        Menu previous = menuRepository.getByDateAndRestaurantId(today, menu.getRestaurant().getId());
        if (previous != null) {
            // Deleting items associated with previous menu
            for (MenuItem mi : previous.getMenuItems()) {
                if (mi.getMenus().size() == 1 && !itemsWithId.containsKey(mi.getId())) {
                    menuItemRepository.deleteById(mi.getId());
                } else {
                    mi.getMenus().remove(previous);
                }
            }
            // Deleting previous menu
            menuRepository.delete(previous.id());
        }

        // Saving a Menu and corresponding MenuItems
        menu.setDate(today);
        menu.getMenuItems().forEach(menuItem -> {
            menuItem.addMenu(menu);
            menuItem.setRestaurantId(restaurantId);
        });
        menuItemRepository.saveAll(menu.getMenuItems());
        Menu result = menuRepository.save(menu);
        return convert(result);
    }
}
