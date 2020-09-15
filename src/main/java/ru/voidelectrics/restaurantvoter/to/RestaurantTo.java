package ru.voidelectrics.restaurantvoter.to;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.voidelectrics.restaurantvoter.model.Menu;

import java.util.Objects;

import static ru.voidelectrics.restaurantvoter.util.ToConversionUtil.convert;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class RestaurantTo {

    private Long id;

    private String name;

    private MenuTo todayMenuTo;

    public RestaurantTo(Long id, String name, Menu todayMenu) {
        this.id = id;
        this.name = name;
        this.todayMenuTo = convert(todayMenu);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantTo that = (RestaurantTo) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(todayMenuTo, that.todayMenuTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, todayMenuTo);
    }
}
