package ru.itis.healthserviceapi.api.exercises;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface ExerciseApi {

    Page<ExerciseTemplateResponse> getTemplates(Pageable pageable);

    List<ExerciseTemplateResponse> searchTemplates(String query);

    List<ExerciseSessionsResponse> getExercisesAtDay(String date);

    void addExercise(ExerciseSessionsRequest request);

    void updateExercise(ExerciseSessionsRequest request);

    void deleteExercise(UUID id);
}
