package ru.voidelectrics.restaurantvoter.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "restaurants")
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Restaurant extends AbstractBaseEntity {
    @Column(name = "name", nullable = false)
    @NotNull
    @Size(min = 2, max = 100)
    private String name;

    public Restaurant(long id, String name) {
        super(id);
        this.name = name;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name +
                '}';
    }
}
