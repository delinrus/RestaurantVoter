package ru.voidelectrics.restaurantvoter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "restaurants")
public class Restaurant extends AbstractBaseEntity {
    @Column(name = "name", nullable = false)
    @NotNull
    @Size(min = 2, max = 100)
    private String name;

    public Restaurant(String name) {
        super(null);
        this.name = name;
    }

    public Restaurant() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restaurant that = (Restaurant) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                '}';
    }
}
