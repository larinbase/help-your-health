package ru.itis.healthserviceapi.dto.response;

public record DateStatsResponse(
        NutritionalInfoResponse consumed,
        NutritionalInfoResponse goal,
        int burnedCalories,
        int waterConsumed,
        int waterGoal
) {
}
