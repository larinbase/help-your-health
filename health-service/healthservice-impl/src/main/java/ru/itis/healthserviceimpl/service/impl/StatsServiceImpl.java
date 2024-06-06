package ru.itis.healthserviceimpl.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.healthserviceapi.dto.request.DayStatsRequest;
import ru.itis.healthserviceapi.dto.response.*;
import ru.itis.healthserviceimpl.model.EatenFood;
import ru.itis.healthserviceimpl.security.userdetails.BaseUserDetails;
import ru.itis.healthserviceimpl.service.*;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {

    private final ExerciseService exerciseService;
    private final EatenFoodService eatenFoodService;
    private final DrinkingWaterService drinkingWaterService;
    private final UserService userService;

    @Override
    public DateStatsResponse getDateStats(DayStatsRequest dayStatsRequest) {
        BaseUserDetails userDetails = (BaseUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<ExerciseSessionResponse> exercises = exerciseService.getExercisesAtDay(dayStatsRequest.date());
        int exerciseCalories = 0;
        for (ExerciseSessionResponse exercise : exercises) {
            exerciseCalories += Math.round(exercise.metricAmount() * exercise.template().caloriesPerUnit());
        }
        List<EatenFoodResponse> eatenFood = eatenFoodService.getByDate(dayStatsRequest.date());
        int eatenFoodCalories = 0;
        int protein = 0;
        int fat = 0;
        int carbohydrate = 0;
        for (EatenFoodResponse food : eatenFood) {
            NutritionalInfoResponse nutrients = food.nutrients();
            eatenFoodCalories += nutrients.calories();
            protein += nutrients.protein();
            fat += nutrients.fat();
            carbohydrate += nutrients.carbohydrates();
        }
        UserResponse user = userService.findByUsername(userDetails.getUsername());
        NutritionalInfoResponse goal = new NutritionalInfoResponse(
                user.calorieAllowance(),
                user.proteins(),
                user.fats(),
                user.carbohydrates()
        );
        NutritionalInfoResponse consumed = new NutritionalInfoResponse(
                eatenFoodCalories - exerciseCalories,
                protein,
                fat,
                carbohydrate
        );
        int waterDrunk = 0;
        for(DrinkingWaterResponse water : drinkingWaterService.findDrinkingByDate(dayStatsRequest.date())){
            waterDrunk += water.milliliters();
        }
        return new DateStatsResponse(
                consumed,
                goal,
                exerciseCalories,
                waterDrunk,
                user.waterNorm()
        );
    }
}
