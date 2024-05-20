package ru.itis.healthserviceimpl.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.itis.healthserviceapi.api.ExerciseApi;
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
@RequestMapping("/api/exercises")
public class ExerciseController implements ExerciseApi {

    private final ExerciseService exerciseService;

    @Override
    @GetMapping("/templates")
    public Page<ExerciseTemplateResponse> getTemplates(Pageable pageable) {
        return exerciseService.getTemplates(pageable);
    }

    @Override
    @GetMapping(value = "/templates", params = {"q"})
    public List<ExerciseTemplateResponse> searchTemplates(@RequestParam("q") String query) {
        return exerciseService.searchTemplates(query);
    }

    @Override
    @PostMapping("/templates")
    public UUID createTemplate(@RequestBody ExerciseTemplateRequest templateRequest) {
        return exerciseService.createTemplate(templateRequest);
    }

    @Override
    @PutMapping("/templates/{templateId}")
    public void updateTemplate(@PathVariable("templateId") UUID templateId, @RequestBody ExerciseTemplateRequest templateRequest) {
        exerciseService.updateTemplate(templateId, templateRequest);
    }


    @Override
    @DeleteMapping("/templates/{templateId}")
    public void deleteTemplate(@PathVariable("templateId") UUID templateId) {
        exerciseService.deleteTemplate(templateId);
    }

    @Override
    @GetMapping("/sessions/{date}")
    public List<ExerciseSessionResponse> getExercisesAtDay(@PathVariable String date) {
        return exerciseService.getExercisesAtDay(date);
    }

    @Override
    @PostMapping("/sessions")
    public void addExercise(@RequestBody  ExerciseSessionRequest request) {
        exerciseService.addExercise(request);
    }

    @Override
    @PutMapping("/sessions/{id}")
    public void updateExercise(@PathVariable UUID id, @RequestBody ExerciseSessionRequest request) {
        exerciseService.updateExercise(id, request);
    }

    @Override
    @DeleteMapping("/sessions/{id}")
    public void deleteExercise(@PathVariable  UUID id) {
        exerciseService.deleteExercise(id);
    }
}
