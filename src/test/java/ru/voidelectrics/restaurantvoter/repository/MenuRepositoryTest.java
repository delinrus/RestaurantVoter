package ru.voidelectrics.restaurantvoter.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.voidelectrics.restaurantvoter.model.Menu;

import java.time.LocalDate;

import static ru.voidelectrics.restaurantvoter.RestaurantTestData.RESTAURANT1;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
class MenuRepositoryTest {

    @Autowired
    private MenuRepository repository;

    @Test
    void getByDateAndRestaurantId() throws Exception {
        Menu menu = repository.getByDateAndRestaurantId(LocalDate.of(2020, 8,22), RESTAURANT1.id());
        System.out.println(menu);
    }
}