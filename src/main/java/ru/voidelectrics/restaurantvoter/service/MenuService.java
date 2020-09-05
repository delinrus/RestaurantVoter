package ru.voidelectrics.restaurantvoter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.voidelectrics.restaurantvoter.model.Menu;
import ru.voidelectrics.restaurantvoter.repository.MenuRepository;


import java.util.List;

@Service
public class MenuService {
    @Autowired
    private final MenuRepository repository;

    public MenuService(MenuRepository repository) {
        this.repository = repository;
    }

    public List<Menu> getAll() {
        return repository.findAll();
    }
}
