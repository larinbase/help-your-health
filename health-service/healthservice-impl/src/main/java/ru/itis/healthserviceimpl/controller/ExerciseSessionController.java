package ru.itis.healthserviceimpl.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.healthserviceapi.api.ExerciseSessionApi;
import ru.itis.healthserviceapi.dto.request.ExerciseSessionRequest;
import ru.itis.healthserviceapi.dto.request.ExerciseTemplateRequest;
import ru.itis.healthserviceapi.dto.response.ExerciseSessionResponse;
import ru.itis.healthserviceapi.dto.response.ExerciseTemplateResponse;
import ru.itis.healthserviceimpl.service.ExerciseService;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ExerciseSessionController implements ExerciseSessionApi {

    private final ExerciseService exerciseService;

    @Override
    @PreAuthorize("@ExerciseSessionRoleService.hasAnyRoleByExerciseSessionId(null, @ExerciseSessionRoleType.VIEWER)")
    public List<ExerciseSessionResponse> getExercisesAtDay(String date) {
        return exerciseService.getExercisesAtDay(date);
    }

    @Override
    public void addExercise(ExerciseSessionRequest request) {
        exerciseService.addExercise(request);
    }

    @Override
    @PreAuthorize("@ExerciseSessionRoleService.hasAnyRoleByExerciseSessionId(#id, @ExerciseSessionRoleType.EDITOR)")
    public void updateExercise(UUID id, ExerciseSessionRequest request) {
        exerciseService.updateExercise(id, request);
    }

    @Override
    @PreAuthorize("@ExerciseSessionRoleService.hasAnyRoleByExerciseSessionId(#id, @ExerciseSessionRoleType.EDITOR)")
    public void deleteExercise(UUID id) {
        exerciseService.deleteExercise(id);
    }
}
