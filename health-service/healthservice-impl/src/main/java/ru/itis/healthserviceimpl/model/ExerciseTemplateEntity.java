package ru.itis.healthserviceimpl.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "exercise_template")
public class ExerciseTemplateEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private UUID id;

    @Column(nullable = false)
    private String description;

    private String image;

    @Column(nullable = false)
    private float caloriesPerUnit;

    @Column(nullable = false)
    private String metricUnit;

    @Column(nullable = false)
    private boolean isCustom;
}
