package ru.voidelectrics.restaurantvoter.util;

import ru.voidelectrics.restaurantvoter.model.Role;
import ru.voidelectrics.restaurantvoter.model.User;
import ru.voidelectrics.restaurantvoter.to.UserTo;

public class UserUtil {

    public static User createNewFromTo(UserTo userTo) {
        return new User(null, userTo.getEmail(), userTo.getPassword(), Role.USER);
    }

    public static User updateFromTo(User user, UserTo userTo) {
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPassword(userTo.getPassword());
        return user;
    }

}
