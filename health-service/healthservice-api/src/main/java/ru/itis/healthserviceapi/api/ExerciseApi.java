package ru.itis.healthserviceapi.api;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.itis.healthserviceapi.dto.request.ExerciseSessionRequest;
import ru.itis.healthserviceapi.dto.request.ExerciseTemplateRequest;
import ru.itis.healthserviceapi.dto.response.ExerciseSessionResponse;
import ru.itis.healthserviceapi.dto.response.ExerciseTemplateResponse;

import java.util.List;
import java.util.UUID;

public interface ExerciseApi {

    Page<ExerciseTemplateResponse> getTemplates(Pageable pageable);

    List<ExerciseTemplateResponse> searchTemplates(String query);

    UUID createTemplate(ExerciseTemplateRequest templateRequest);

    void updateTemplate(UUID templateId, ExerciseTemplateRequest templateRequest);

    void deleteTemplate(UUID templateId);


    List<ExerciseSessionResponse> getExercisesAtDay(String date);

    void addExercise(ExerciseSessionRequest request);

    void updateExercise(UUID id, ExerciseSessionRequest request);

    void deleteExercise(UUID id);
}
