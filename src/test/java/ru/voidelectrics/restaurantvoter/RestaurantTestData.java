package ru.voidelectrics.restaurantvoter;

import ru.voidelectrics.restaurantvoter.model.Restaurant;

import java.util.List;

import static ru.voidelectrics.restaurantvoter.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static TestMatcher<Restaurant> RESTAURANT_MATCHER = TestMatcher.usingEqualsComparator(Restaurant.class);

    public static final int RESTAURANT1_ID = START_SEQ;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "Континенталь");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT1_ID + 1, "Дон помидор");

    public static final List<Restaurant> RESTAURANTS = List.of(RESTAURANT1, RESTAURANT2);
}
