package ru.voidelectrics.restaurantvoter.to;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.voidelectrics.restaurantvoter.model.Menu;

import java.util.Objects;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class RestaurantTo {

    private Long id;

    private String name;

    private Menu todayMenu;

    private int todayUserVoted;

    public RestaurantTo(Long id, String name, Menu todayMenu, int todayUserVoted) {
        this.id = id;
        this.name = name;
        this.todayMenu = todayMenu;
        this.todayUserVoted = todayUserVoted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantTo that = (RestaurantTo) o;
        return todayUserVoted == that.todayUserVoted &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(todayMenu, that.todayMenu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, todayMenu, todayUserVoted);
    }
}
