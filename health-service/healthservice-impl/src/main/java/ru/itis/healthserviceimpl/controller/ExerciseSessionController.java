package ru.itis.healthserviceimpl.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@RequestMapping("/api/exercises/sessions")
public class ExerciseSessionController implements ExerciseSessionApi {

    private final ExerciseService exerciseService;

    @Override
    @GetMapping("/{date}")
    public List<ExerciseSessionResponse> getExercisesAtDay(@PathVariable String date) {
        return exerciseService.getExercisesAtDay(date);
    }

    @Override
    @PostMapping
    public void addExercise(@RequestBody  ExerciseSessionRequest request) {
        exerciseService.addExercise(request);
    }

    @Override
    @PutMapping("/{id}")
    public void updateExercise(@PathVariable UUID id, @RequestBody ExerciseSessionRequest request) {
        exerciseService.updateExercise(id, request);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteExercise(@PathVariable  UUID id) {
        exerciseService.deleteExercise(id);
    }
}
