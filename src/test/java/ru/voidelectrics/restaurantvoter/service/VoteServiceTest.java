package ru.voidelectrics.restaurantvoter.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.voidelectrics.restaurantvoter.model.Vote;

import java.time.LocalDate;

import static ru.voidelectrics.restaurantvoter.UserTestData.ADMIN_ID;
import static ru.voidelectrics.restaurantvoter.UserTestData.USER_ID;

@SpringJUnitConfig(locations = {
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
class VoteServiceTest {

    @Autowired
    private VoteService service;

    @Test
    void getByDateAndUserId() throws Exception {
        Vote vote = service.getByDateAndUserId(LocalDate.parse("2020-08-22"), ADMIN_ID);
        System.out.println(vote);
    }

}