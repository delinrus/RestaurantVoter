package ru.voidelectrics.restaurantvoter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.voidelectrics.restaurantvoter.model.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{
}
