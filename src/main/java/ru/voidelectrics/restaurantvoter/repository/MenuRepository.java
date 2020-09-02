package ru.voidelectrics.restaurantvoter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.voidelectrics.restaurantvoter.model.Menu;

public interface MenuRepository extends JpaRepository<Menu, Long> {

}
