package ru.voidelectrics.restaurantvoter.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.voidelectrics.restaurantvoter.model.Restaurant;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class MenuTo {

    @NotNull
    private Restaurant restaurant;

    @Valid
    @JsonProperty("menuItems")
    private List<MenuItemTo> menuItemTos;
}
