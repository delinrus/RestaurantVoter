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
import java.util.*;

@Entity
@Table(name = "menus", uniqueConstraints = {@UniqueConstraint(columnNames = {"restaurant_id", "date"}, name = "menus_unique_restaurant_date_idx")})
@Setter
@Getter
@ToString
@NoArgsConstructor
public class Menu extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    @Column(name = "date", nullable = false)
    @JsonFormat(pattern = DateUtil.FORMAT)
    @NotNull
    private LocalDate date;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="menu_menu_item",
            joinColumns=@JoinColumn(name="menu_id"),
            inverseJoinColumns=@JoinColumn(name="menu_item_id"))
   // @JsonManagedReference
    private Set<MenuItem> menuItems = new HashSet<>();

    public Menu(Long id, Restaurant restaurant, LocalDate date, MenuItem menuItem, MenuItem... menuItems) {
        super(id);
        this.restaurant = restaurant;
        this.date = date;
        Set<MenuItem> set = new HashSet<>();
        set.add(menuItem);
        set.addAll(Arrays.asList(menuItems));
        this.menuItems = set;
        this.menuItems.forEach(mi -> mi.addMenu(this));
    }
}
