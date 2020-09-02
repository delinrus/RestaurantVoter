package ru.voidelectrics.restaurantvoter.service;

import org.springframework.stereotype.Service;
import ru.voidelectrics.restaurantvoter.model.Vote;
import ru.voidelectrics.restaurantvoter.repository.VoteRepository;

import java.util.List;

@Service
public class VoteService {
    private final VoteRepository repository;

    public VoteService(VoteRepository repository) {
        this.repository = repository;
    }

    public List<Vote> getAll() {
        return repository.findAll();
    }
}
