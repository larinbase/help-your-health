package ru.itis.healthserviceimpl.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "account")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User extends AbstractModel {
    private String username;
    private String firstname;
    private String lastname;
    private int age;
    private int weight;
    private int height;

    @Column(name = "calorie_allowance")
    private int calorieAllowance;

    @Column(name = "water_norm")
    private int waterNorm;
}
