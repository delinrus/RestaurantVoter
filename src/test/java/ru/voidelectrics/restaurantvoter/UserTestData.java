package ru.voidelectrics.restaurantvoter;

import ru.voidelectrics.restaurantvoter.model.Role;
import ru.voidelectrics.restaurantvoter.model.User;

import java.util.Collections;

import static ru.voidelectrics.restaurantvoter.model.AbstractBaseEntity.START_SEQ;

public class UserTestData {

    public static TestMatcher<User> USER_MATCHER = TestMatcher.usingFieldsWithIgnoringAssertions(User.class, "password");

    public static final long USER_ID = START_SEQ + 6;
    public static final long ADMIN_ID = START_SEQ + 7;

    public static final User USER = new User(USER_ID, "user@mail.ru", "user", Role.USER);
    public static final User ADMIN = new User(ADMIN_ID, "admin@mail.ru", "admin", Role.ADMIN, Role.USER);

    public static User getNew() {
        return new User(null, "new@gmail.com", "newPass", Role.USER);
    }

    public static User getUpdated() {
        User updated = new User(USER);
        updated.setEmail("updated@mail.ru");
        updated.setRoles(Collections.singletonList(Role.ADMIN));
        return updated;
    }
}
