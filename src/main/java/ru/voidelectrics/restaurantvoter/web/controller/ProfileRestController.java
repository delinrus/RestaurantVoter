package ru.voidelectrics.restaurantvoter.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.voidelectrics.restaurantvoter.model.User;
import ru.voidelectrics.restaurantvoter.service.UserService;
import ru.voidelectrics.restaurantvoter.to.UserTo;

import java.net.URI;

import static ru.voidelectrics.restaurantvoter.web.SecurityUtil.authUserId;

@RestController
@RequestMapping(ProfileRestController.REST_URL)
public class ProfileRestController {
    static final String REST_URL = "/rest/profile";

    @Autowired
    private UserService service;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User get() {
        return service.get(authUserId());
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete() {
        service.delete(authUserId());
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ResponseEntity<User> register(@RequestBody UserTo userTo) {
        User created = service.create(userTo);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL).build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody UserTo userTo) {
        long fooId = authUserId();
        service.update(userTo, authUserId());
    }
}