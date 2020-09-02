package ru.voidelectrics.restaurantvoter.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "menu_items")
@Setter
@Getter
@ToString
@NoArgsConstructor
public class MenuItem extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    @NotNull
    private Menu menu;

    @Column(name = "name", nullable = false)
    @NotBlank
    @Size(min = 2, max = 200)
    private String name;

    // Price measured in cents
    @Column(name = "price", nullable = false)
    @Range(min = 1)
    private int price;
}
