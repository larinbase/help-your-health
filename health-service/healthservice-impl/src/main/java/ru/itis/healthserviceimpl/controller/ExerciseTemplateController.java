package ru.itis.healthserviceimpl.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("@ExerciseTemplateRoleService.hasAnyRoleByExerciseTemplateId(null , @ExerciseTemplateRoleType.VIEWER)")
    public Page<ExerciseTemplateResponse> getTemplates(Pageable pageable) {
        return exerciseService.getTemplates(pageable);
    }

    @Override
    @PreAuthorize("@ExerciseTemplateRoleService.hasAnyRoleByExerciseTemplateId(null, @ExerciseTemplateRoleType.VIEWER)")
    public List<ExerciseTemplateResponse> searchTemplates(String query) {
        return exerciseService.searchTemplates(query);
    }

    @Override
    @PreAuthorize("@ExerciseTemplateRoleService.hasAnyRoleByExerciseTemplateId(null, @ExerciseTemplateRoleType.EDITOR)")
    public UUID createTemplate(ExerciseTemplateRequest templateRequest) {
        return exerciseService.createTemplate(templateRequest);
    }

    @Override
    @PreAuthorize(
            "@ExerciseTemplateRoleService.hasAnyRoleByExerciseTemplateId(#templateId, @ExerciseTemplateRoleType.EDITOR)"
    )
    public void updateTemplate(UUID templateId, ExerciseTemplateRequest templateRequest) {
        exerciseService.updateTemplate(templateId, templateRequest);
    }


    @Override
    @PreAuthorize(
            "@ExerciseTemplateRoleService.hasAnyRoleByExerciseTemplateId(#templateId, @ExerciseTemplateRoleType.EDITOR)"
    )
    public void deleteTemplate(UUID templateId) {
        exerciseService.deleteTemplate(templateId);
    }
}
