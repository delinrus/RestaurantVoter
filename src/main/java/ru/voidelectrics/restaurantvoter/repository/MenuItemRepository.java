package ru.voidelectrics.restaurantvoter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.voidelectrics.restaurantvoter.model.MenuItem;


public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}
