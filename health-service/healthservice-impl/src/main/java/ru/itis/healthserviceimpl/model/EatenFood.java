package ru.itis.healthserviceimpl.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;

@Getter
@Entity
@Table(name = "eaten_foods")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class EatenFood extends AbstractModel {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    @Column(nullable = false, unique = false)
    private Short weight;

    @CreationTimestamp
    @Column(name = "created_date", nullable = false, unique = false)
    private Instant createdDate;

}
