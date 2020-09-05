package ru.voidelectrics.restaurantvoter;

import ru.voidelectrics.restaurantvoter.model.Role;
import ru.voidelectrics.restaurantvoter.model.User;

import static ru.voidelectrics.restaurantvoter.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {

    public static final long USER_ID = START_SEQ + 6;
    public static final long ADMIN_ID = START_SEQ + 7;

    public static final User USER = new User(USER_ID, "user@mail.ru", "user", Role.USER);
    public static final User ADMIN = new User(ADMIN_ID, "admin@mail.ru", "admin", Role.ADMIN, Role.USER);
}
