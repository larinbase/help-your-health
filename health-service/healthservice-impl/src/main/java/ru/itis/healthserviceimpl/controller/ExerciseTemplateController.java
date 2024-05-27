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
public class ExerciseTemplateController implements ExerciseTemplateApi {

    private final ExerciseService exerciseService;


    @Override
    public Page<ExerciseTemplateResponse> getTemplates(Pageable pageable) {
        return exerciseService.getTemplates(pageable);
    }

    @Override
    public List<ExerciseTemplateResponse> searchTemplates(String query) {
        return exerciseService.searchTemplates(query);
    }

    @Override
    public UUID createTemplate(ExerciseTemplateRequest templateRequest) {
        return exerciseService.createTemplate(templateRequest);
    }

    @Override
    public void updateTemplate(UUID templateId, ExerciseTemplateRequest templateRequest) {
        exerciseService.updateTemplate(templateId, templateRequest);
    }


    @Override
    public void deleteTemplate(UUID templateId) {
        exerciseService.deleteTemplate(templateId);
    }
}
