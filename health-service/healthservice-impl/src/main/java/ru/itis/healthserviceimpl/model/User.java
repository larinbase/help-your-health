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
    private Sex sex;
    private int age;
    private int weight;
    private int height;
    private ActivityCoefficient activityCoefficient;

    @Column(name = "calorie_allowance")
    private int calorieAllowance;

    private int proteins;
    private int fats;
    private int carbohydrates;

    @Column(name = "water_norm")
    private int waterNorm;
}
