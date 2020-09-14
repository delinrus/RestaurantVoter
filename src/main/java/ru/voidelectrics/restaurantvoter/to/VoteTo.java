package ru.voidelectrics.restaurantvoter.to;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.voidelectrics.restaurantvoter.HasId;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
public class VoteTo implements HasId {
    private Long id;

    @NotNull
    private Long restaurantId;

    public VoteTo(@NotNull Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
