package ru.voidelectrics.restaurantvoter.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "restaurants")
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Restaurant extends AbstractBaseEntity {
    @Column(name = "name", nullable = false)
    @NotNull
    @Size(min = 2, max = 100)
    private String name;

    public Restaurant(String name) {
        super(null);
        this.name = name;
    }
}
