package ru.itis.healthserviceimpl.util;

import ru.itis.healthserviceimpl.model.ActivityCoefficient;
import ru.itis.healthserviceimpl.model.Nutrition;
import ru.itis.healthserviceimpl.model.Sex;

import java.util.HashMap;
import java.util.Map;

public class CalculateNutritionalInfoAndWaterNorm {

    public static Map<Nutrition, Integer> calculateNutritionalInfo(Sex sex, int weight, int height, int age,
                                                                ActivityCoefficient activityCoefficient) {
        Map<Nutrition, Integer> nutritionalInfo = new HashMap<>();
        int calories = calculateCalories(sex, weight, height, age, activityCoefficient);
        nutritionalInfo.put(Nutrition.CALORIES, calories);
        nutritionalInfo.put(Nutrition.PROTEINS, calculateProteins(calories));
        nutritionalInfo.put(Nutrition.FATS, calculateFats(calories));
        nutritionalInfo.put(Nutrition.CARBOHYDRATES, calculateCarbohydrates(calories));
        nutritionalInfo.put(Nutrition.WATER_NORM, calculateWaterNorm(weight));
        return nutritionalInfo;
    }

    public static int calculateCalories(Sex sex, int weight, int height, int age,
                                        ActivityCoefficient activityCoefficient) {
        if (sex == Sex.MALE) {
            return (int) ((10 * weight + 6.25 * height - 5 * age + 5) * activityCoefficient.getNumVal());
        } else {
            return (int) ((10 * weight + 6.25 * height - 5 * age - 161) * activityCoefficient.getNumVal());
        }
    }

    public static int calculateProteins(int calories) {
        return calories / 10 * 3 / 4;
    }

    public static int calculateFats(int calories) {
        return calories / 10 * 2 / 4;
    }

    public static int calculateCarbohydrates(int calories) {
        return calories / 10 * 5 / 4;
    }

    public static int calculateWaterNorm(int weight) {
        return weight * 30;
    }
}
