package ru.voidelectrics.restaurantvoter.to;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class VoteTo {

    @NotNull
    private Long restaurantId;

    public VoteTo(@NotNull Long restaurantId) {
        this.restaurantId = restaurantId;
    }
}
