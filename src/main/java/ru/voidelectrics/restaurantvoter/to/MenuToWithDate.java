package ru.voidelectrics.restaurantvoter.to;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ru.voidelectrics.restaurantvoter.util.DateUtil;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MenuToWithDate  {

    @NotNull
    private Long restaurantId;

    @Valid
    @JsonProperty("menuItems")
    private List<MenuItemTo> menuItemTos;

    @JsonFormat(pattern = DateUtil.FORMAT)
    @NotNull
    private LocalDate date;
}
