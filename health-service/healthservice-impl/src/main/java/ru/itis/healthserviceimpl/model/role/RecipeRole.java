package ru.itis.healthserviceimpl.model.role;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import ru.itis.healthserviceimpl.model.User;
import ru.itis.healthserviceimpl.model.roletype.RecipeRoleType;

import java.util.UUID;

@Entity
@Table(name = "recipe_role")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RecipeRole {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", type = org.hibernate.id.UUIDGenerator.class)
    protected UUID id;

    private RecipeRoleType type;

    @Column(name = "recipe_id")
    private UUID recipeId;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
}
