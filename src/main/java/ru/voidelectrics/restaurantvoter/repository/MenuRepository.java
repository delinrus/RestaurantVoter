package ru.voidelectrics.restaurantvoter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.voidelectrics.restaurantvoter.model.Menu;

import java.time.LocalDate;

public interface MenuRepository extends JpaRepository<Menu, Long> {

    Menu getByDateAndRestaurantId(LocalDate date, long restaurantId);

    @Modifying
    @Query("DELETE FROM Menu m WHERE m.id=:id")
    int delete(@Param("id") long id);
}
