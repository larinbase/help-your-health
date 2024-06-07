package ru.itis.healthserviceimpl.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import ru.itis.healthserviceimpl.model.roles.EatenFoodRoleType;
import ru.itis.healthserviceimpl.model.roles.ExerciseSessionRoleType;

import java.util.UUID;

@Entity
@Table(name = "eaten_food_role")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class EatenFoodRole {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", type = org.hibernate.id.UUIDGenerator.class)
    protected UUID id;

    private EatenFoodRoleType type;

    @ManyToOne
    @JoinColumn(name = "eaten_food_id", nullable = false)
    private EatenFood eatenFood;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
