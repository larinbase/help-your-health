package ru.itis.healthserviceimpl.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

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
    private String password;

    @Column(name = "calorie_allowance")
    private int calorieAllowance;

    @Column(name = "water_norm")
    private int waterNorm;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private CommunityRole role;

    @OneToMany
    private List<RecipeRole> receiptRoles;
}
