package ru.itis.healthserviceimpl.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "exercise_session")
public class ExerciseSessionEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private float metricAmount;

    @Column(nullable = false, name = "user_id")
    private UUID userId;

    @Column(nullable = false, name = "template_id")
    private UUID templateId;


    @Column(nullable = false)
    private Date date;


    @ManyToOne
    @JoinColumn(
            name = "template_id",
            nullable = false,
            insertable = false,
            updatable = false
    )
    private ExerciseTemplateEntity template;

    @ManyToOne
    @JoinColumn(
            name = "user_id", nullable = false,
            insertable = false,
            updatable = false
    )
    private User user;

}
