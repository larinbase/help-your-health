package ru.itis.healthserviceimpl.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import ru.itis.healthserviceimpl.model.roles.RecipeRoleType;

import java.util.UUID;

@Entity
@Table(name = "recipe_role")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RecipeRole extends AbstractModel {
    private RecipeRoleType type;

    @Column(name = "recipe_id")
    private UUID recipeId;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
