package ru.voidelectrics.restaurantvoter.service;

import org.hsqldb.HsqlException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.voidelectrics.restaurantvoter.model.Restaurant;

import static ru.voidelectrics.restaurantvoter.TestUtil.validateRootCause;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Sql(scripts = "classpath:db/populateDB-test.sql", config = @SqlConfig(encoding = "UTF-8"))
class RestaurantServiceTest {

    @Autowired
    private RestaurantService service;

    @Test
    void createWithNotUniqueName() throws Exception {
        validateRootCause(() -> service.create(new Restaurant(null, "Континенталь")), HsqlException.class);
    }
}