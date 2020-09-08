package ru.voidelectrics.restaurantvoter.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.voidelectrics.restaurantvoter.util.DateUtil;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Entity
@Table(name = "menus", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "date"}, name = "menus_unique_restaurant_date_idx")})
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Menu extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    @Column(name = "date", nullable = false)
    @JsonFormat(pattern = DateUtil.FORMAT)
    @NotNull
    private LocalDate date;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "menu")
    @JsonManagedReference
    private List<MenuItem> menuItems;

    public Menu(Long id, Restaurant restaurant, LocalDate date, MenuItem menuItem, MenuItem... menuItems) {
        super(id);
        this.restaurant = restaurant;
        this.date = date;
        List<MenuItem> list = new ArrayList<>();
        list.add(menuItem);
        list.addAll(Arrays.asList(menuItems));
        this.menuItems = list;
        this.menuItems.forEach(mi -> mi.setMenu(this));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Menu menu = (Menu) o;
        return Objects.equals(restaurant, menu.restaurant) &&
                Objects.equals(date, menu.date) &&
                Objects.equals(menuItems, menu.menuItems);
    }

}
