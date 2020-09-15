package ru.voidelectrics.restaurantvoter.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.voidelectrics.restaurantvoter.util.DateUtil;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "votes",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date"}, name = "votes_unique_user_date_idx")},
        indexes = {@Index(columnList = "date", name = "votes_date_idx")})
@Setter
@Getter
@NoArgsConstructor
public class Vote extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    @JsonIgnore
    private User user;

    @Column(name="restaurant_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Long restaurantId;

    @Column(name = "date")
    @JsonFormat(pattern = DateUtil.FORMAT)
    @NotNull
    private LocalDate date;

    public Vote(Long id, Restaurant restaurant, LocalDate date) {
        super(id);
        this.restaurantId = restaurant.getId();
        this.date = date;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + id +
                ", restaurantId=" + restaurantId +
                ", date=" + date +
                '}';
    }
}
