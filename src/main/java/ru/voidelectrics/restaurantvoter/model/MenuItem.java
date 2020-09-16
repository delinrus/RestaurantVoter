package ru.voidelectrics.restaurantvoter.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "menu_items")
@Setter
@Getter
@NoArgsConstructor
public class MenuItem extends AbstractBaseEntity {

    @Column(name = "restaurant_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Long restaurantId;

    @ManyToMany(mappedBy = "menuItems")
    private Set<Menu> menus = new HashSet<Menu>();

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(min = 2, max = 200)
    private String name;

    // Price measured in cents
    @Column(name = "price", nullable = false)
    @Range(min = 1)
    private int price;

    public MenuItem(Long id, Long restaurantId, String name, int price) {
        super(id);
        this.restaurantId = restaurantId;
        this.name = name;
        this.price = price;
    }

    public void setMenus(Set<Menu> menus) {
        this.menus = menus;
    }

    public void addMenu(Menu menu) {
        menus.add(menu);
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "id=" + id +
                ", restaurantId=" + restaurantId +
                ", name=" + name +
                ", price=" + price +
                '}';
    }
}
