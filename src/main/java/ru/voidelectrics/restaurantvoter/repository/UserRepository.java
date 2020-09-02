package ru.voidelectrics.restaurantvoter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.voidelectrics.restaurantvoter.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
