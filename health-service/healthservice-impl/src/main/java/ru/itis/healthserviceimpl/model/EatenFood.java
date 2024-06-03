package ru.itis.healthserviceimpl.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "eaten_foods")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class EatenFood extends AbstractModel {

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "food_id")
    private Food food;

    private UUID recipeId;

    @Column(nullable = false, unique = false)
    private Short weight;
}
