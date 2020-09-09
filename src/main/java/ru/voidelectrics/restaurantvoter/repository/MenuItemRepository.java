package ru.voidelectrics.restaurantvoter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import ru.voidelectrics.restaurantvoter.model.MenuItem;

@Transactional(readOnly = true)
public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}
