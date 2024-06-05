package ru.itis.healthserviceimpl.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Getter
@Entity
@Table(name = "foods")
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Food extends AbstractModel implements Consumable{

    @Column(nullable = false, unique = false)
    private String name;

    @Column(nullable = false, unique = false)
    private Short proteins;

    @Column(nullable = false, unique = false)
    private Short fats;

    @Column(nullable = false, unique = false)
    private Short carbohydrates;

    @Column(name = "calories", nullable = false, unique = false)
    private Short calories;

    @Column(name = "type_of_food", nullable = false, unique = false)
    private Short typeOfFood;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false, unique = false)
    private FoodCategory category;

    @OneToMany(mappedBy = "food")
    private List<EatenFood> users;

    @Override
    public NutritionalInfo getNutrients() {
        return new NutritionalInfo(calories, proteins, fats, carbohydrates);
    }
}
