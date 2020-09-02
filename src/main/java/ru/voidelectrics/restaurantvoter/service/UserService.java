package ru.voidelectrics.restaurantvoter.service;

import org.springframework.stereotype.Service;
import ru.voidelectrics.restaurantvoter.model.User;
import ru.voidelectrics.restaurantvoter.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAll() {
        return repository.findAll();
    }

}
