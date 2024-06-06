package ru.itis.healthserviceimpl.model.role;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.GenericGenerator;
import ru.itis.healthserviceimpl.model.ExerciseSessionEntity;
import ru.itis.healthserviceimpl.model.User;
import ru.itis.healthserviceimpl.model.roletype.ExerciseSessionRoleType;

import java.util.UUID;

@Entity
@Table(name = "exercise_session_role")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ExerciseSessionRole {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", type = org.hibernate.id.UUIDGenerator.class)
    protected UUID id;

    private ExerciseSessionRoleType type;

    @ManyToOne
    @JoinColumn(name = "exercise_session_entity_id", nullable = false)
    private ExerciseSessionEntity exerciseSessionEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
