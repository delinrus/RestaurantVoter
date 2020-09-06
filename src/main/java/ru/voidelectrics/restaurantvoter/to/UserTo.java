package ru.voidelectrics.restaurantvoter.to;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@NoArgsConstructor
@ToString
@Getter
@Setter
public class UserTo {

    private Integer id;

    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    @NotBlank
    @Size(min = 5, max = 100)
    private String password;

    public UserTo(Integer id, String email, String password) {
        this.id = id;
        this.email = email;
        this.password = password;
    }
}
