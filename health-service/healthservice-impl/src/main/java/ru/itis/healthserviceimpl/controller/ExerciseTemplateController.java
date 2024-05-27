package ru.itis.healthserviceimpl.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import ru.itis.healthserviceapi.api.ExerciseTemplateApi;
import ru.itis.healthserviceapi.dto.request.ExerciseTemplateRequest;
import ru.itis.healthserviceapi.dto.response.ExerciseTemplateResponse;
import ru.itis.healthserviceimpl.service.ExerciseService;

import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exercises/templates")
public class ExerciseTemplateController implements ExerciseTemplateApi {

    private final ExerciseService exerciseService;


    @Override
    @GetMapping()
    public Page<ExerciseTemplateResponse> getTemplates(Pageable pageable) {
        return exerciseService.getTemplates(pageable);
    }

    @Override
    @GetMapping(params = {"q"})
    public List<ExerciseTemplateResponse> searchTemplates(@RequestParam("q") String query) {
        return exerciseService.searchTemplates(query);
    }

    @Override
    @PostMapping()
    public UUID createTemplate(@RequestBody ExerciseTemplateRequest templateRequest) {
        return exerciseService.createTemplate(templateRequest);
    }

    @Override
    @PutMapping("/{templateId}")
    public void updateTemplate(@PathVariable("templateId") UUID templateId, @RequestBody ExerciseTemplateRequest templateRequest) {
        exerciseService.updateTemplate(templateId, templateRequest);
    }


    @Override
    @DeleteMapping("/{templateId}")
    public void deleteTemplate(@PathVariable("templateId") UUID templateId) {
        exerciseService.deleteTemplate(templateId);
    }
}
