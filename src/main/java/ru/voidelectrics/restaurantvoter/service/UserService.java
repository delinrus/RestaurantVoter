package ru.voidelectrics.restaurantvoter.service;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.voidelectrics.restaurantvoter.AuthorizedUser;
import ru.voidelectrics.restaurantvoter.model.User;
import ru.voidelectrics.restaurantvoter.repository.UserRepository;
import ru.voidelectrics.restaurantvoter.to.UserTo;
import ru.voidelectrics.restaurantvoter.util.UserUtil;

import java.util.List;

import static ru.voidelectrics.restaurantvoter.util.ValidationUtil.checkNotFoundWithId;

@Service("userService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService implements UserDetailsService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> getAll() {
        return repository.findAll();
    }

    public User get(long id) {
        User result = repository.findById(id).orElse(null);
        return checkNotFoundWithId(result, id);
    }

    public void delete(long id) {
        checkNotFoundWithId(repository.delete(id) != 0, id);
    }

    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }

    public User create(UserTo userTo) {
        return repository.save(UserUtil.createNewFromTo(userTo));
    }

    public void update(UserTo userTo, long id) {
        User user = get(id);
        User updateUser = UserUtil.updateFromTo(user, userTo);
        repository.save(updateUser);
    }

    public void update(User user) {
        Assert.notNull(user, "user must not be null");
        repository.save(user);
    }

    public User create(User user) {
        return repository.save(user);
    }

    public User getByMail(String email) {
        return repository.getByEmail(email);
    }
}
