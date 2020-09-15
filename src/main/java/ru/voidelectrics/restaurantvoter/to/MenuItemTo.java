package ru.voidelectrics.restaurantvoter.to;

import lombok.*;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class MenuItemTo {

    @NotBlank
    @Size(min = 2, max = 200)
    private String name;

    // Price measured in cents
    @Range(min = 1)
    private int price;
}
