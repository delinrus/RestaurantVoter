package ru.voidelectrics.restaurantvoter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.voidelectrics.restaurantvoter.model.Vote;

public interface VoteRepository extends JpaRepository<Vote, Long> {
}
