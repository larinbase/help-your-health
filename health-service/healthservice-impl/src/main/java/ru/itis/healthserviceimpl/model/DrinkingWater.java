package ru.itis.healthserviceimpl.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.Instant;
import java.util.UUID;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "drinking_water")
public class DrinkingWater {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @CreationTimestamp
    @Column(name = "create_date")
    private Instant createDate;

    @Column(name = "milliliters")
    private int milliliters;

    @JoinColumn(name = "account_id")
    @ManyToOne
    private User user;

}
