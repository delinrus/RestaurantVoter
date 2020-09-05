package ru.voidelectrics.restaurantvoter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.voidelectrics.restaurantvoter.model.Menu;

import java.time.LocalDate;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    Menu getByDateAndRestaurantId(LocalDate date, long restaurantId);
}
