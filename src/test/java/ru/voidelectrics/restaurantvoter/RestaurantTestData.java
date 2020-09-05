package ru.voidelectrics.restaurantvoter;

import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;
import ru.voidelectrics.restaurantvoter.model.Restaurant;
import ru.voidelectrics.restaurantvoter.to.RestaurantTo;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.voidelectrics.restaurantvoter.MenuTestData.MENU1;
import static ru.voidelectrics.restaurantvoter.MenuTestData.MENU3;
import static ru.voidelectrics.restaurantvoter.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    //public static TestMatcher<Restaurant> RESTAURANT_MATCHER = TestMatcher.usingEqualsComparator(Restaurant.class);
    public static final long RESTAURANT1_ID = START_SEQ;
    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "Континенталь");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT1_ID + 1, "Дон помидор");
    public static final List<Restaurant> RESTAURANTS = List.of(RESTAURANT1, RESTAURANT2);

    public static TestMatcher<RestaurantTo> RESTAURANT_TO_MATCHER = TestMatcher.usingEqualsAssertions(RestaurantTo.class);
    public static final RestaurantTo RESTAURANT_TO1 = new RestaurantTo(RESTAURANT1_ID, "Континенталь", MENU1, 3);
    public static final RestaurantTo RESTAURANT_TO2 = new RestaurantTo(RESTAURANT1_ID + 1, "Дон помидор", MENU3, 0);
    public static final List<RestaurantTo> RESTAURANTS_TOS = List.of(RESTAURANT_TO1, RESTAURANT_TO2);
}
