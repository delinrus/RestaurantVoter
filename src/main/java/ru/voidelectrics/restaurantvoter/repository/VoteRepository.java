package ru.voidelectrics.restaurantvoter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.voidelectrics.restaurantvoter.model.Vote;

import java.time.LocalDate;

public interface VoteRepository extends JpaRepository<Vote, Long> {

    int countByDateAndRestaurantId(LocalDate date, long restaurantId);
}
