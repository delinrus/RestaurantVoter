package ru.voidelectrics.restaurantvoter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.voidelectrics.restaurantvoter.model.Vote;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface VoteRepository extends JpaRepository<Vote, Long> {

    long countByDateAndRestaurantId(LocalDate date, long restaurantId);

    @Query("SELECT v FROM Vote v WHERE v.user.id=:userId ORDER BY v.date DESC")
    List<Vote> getAll(@Param("userId") long userId);

    Vote getByDateAndUserId(LocalDate date, long userId);
}
