package ru.itis.healthserviceimpl.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import ru.itis.healthserviceimpl.model.roles.ExerciseTemplateRoleType;
import ru.itis.healthserviceimpl.model.roles.RecipeRoleType;

import java.util.UUID;

@Entity
@Table(name = "training_role")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ExerciseTemplateRole {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", type = org.hibernate.id.UUIDGenerator.class)
    protected UUID id;

    private ExerciseTemplateRoleType type;

    @ManyToOne
    @JoinColumn(name = "exercise_template_entity_id", nullable = false)
    private ExerciseTemplateEntity exerciseTemplateEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
