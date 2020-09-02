package ru.voidelectrics.restaurantvoter.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import ru.voidelectrics.restaurantvoter.util.DateUtil;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "menus")
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
}
