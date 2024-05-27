package ru.itis.healthserviceapi.api;

import ru.itis.healthserviceapi.dto.request.ExerciseSessionRequest;
import ru.itis.healthserviceapi.dto.response.ExerciseSessionResponse;

import java.util.List;
import java.util.UUID;

public interface ExerciseSessionApi {

    List<ExerciseSessionResponse> getExercisesAtDay(String date);

    void addExercise(ExerciseSessionRequest request);

    void updateExercise(UUID id, ExerciseSessionRequest request);

    void deleteExercise(UUID id);
}
