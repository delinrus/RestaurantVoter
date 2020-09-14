package ru.voidelectrics.restaurantvoter.to;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class MenuTo {

    @NotNull
    private Long restaurantId;

    @Valid
    @JsonProperty("menuItems")
    private List<MenuItemTo> menuItemTos;

    public MenuTo(Long restaurantId, MenuItemTo menuItem, MenuItemTo... menuItems) {
        this.restaurantId = restaurantId;
        List<MenuItemTo> list = new ArrayList<>();
        list.add(menuItem);
        list.addAll(Arrays.asList(menuItems));
        this.menuItemTos = list;
    }
}
