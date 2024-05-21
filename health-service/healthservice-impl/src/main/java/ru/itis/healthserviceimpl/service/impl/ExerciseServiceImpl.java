package ru.itis.healthserviceimpl.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.itis.healthserviceapi.dto.request.ExerciseSessionRequest;
import ru.itis.healthserviceapi.dto.request.ExerciseTemplateRequest;
import ru.itis.healthserviceapi.dto.response.ExerciseSessionResponse;
import ru.itis.healthserviceapi.dto.response.ExerciseTemplateResponse;
import ru.itis.healthserviceimpl.mapper.ExerciseMapper;
import ru.itis.healthserviceimpl.model.ExerciseSessionEntity;
import ru.itis.healthserviceimpl.model.ExerciseTemplateEntity;
import ru.itis.healthserviceimpl.repository.ExerciseSessionRepository;
import ru.itis.healthserviceimpl.repository.ExerciseTemplateRepository;
import ru.itis.healthserviceimpl.service.ExerciseService;

import java.sql.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements ExerciseService {

    private final ExerciseTemplateRepository templateRepository;
    private final ExerciseSessionRepository sessionRepository;

    private final ExerciseMapper exerciseMapper;

    public Page<ExerciseTemplateResponse> getTemplates(Pageable pageable) {
        return templateRepository.findAll(pageable).map(exerciseMapper::toResponse);
    }

    public List<ExerciseTemplateResponse> searchTemplates(String query) {
        return templateRepository.findAll().stream()
                .filter(template -> template.getDescription().toLowerCase().contains(query.toLowerCase()))
                .map(exerciseMapper::toResponse)
                .collect(Collectors.toList());
    }

    public UUID createTemplate(ExerciseTemplateRequest templateRequest) {
        ExerciseTemplateEntity template = new ExerciseTemplateEntity();
        template.setDescription(templateRequest.description());
        template.setImage(templateRequest.image());
        template.setCaloriesPerUnit(templateRequest.caloriesPerUnit());
        template.setMetricUnit(templateRequest.metricUnit());
        template.setCustom(templateRequest.isCustom());
        return templateRepository.save(template).getId();
    }

    public void updateTemplate(UUID templateId, ExerciseTemplateRequest templateRequest) {
        ExerciseTemplateEntity template = templateRepository.findById(templateId).orElseThrow(() -> new RuntimeException("Template not found"));
        template.setDescription(templateRequest.description());
        template.setImage(templateRequest.image());
        template.setCaloriesPerUnit(templateRequest.caloriesPerUnit());
        template.setMetricUnit(templateRequest.metricUnit());
        template.setCustom(templateRequest.isCustom());
        templateRepository.save(template);
    }

    public void deleteTemplate(UUID templateId) {
        templateRepository.deleteById(templateId);
    }

    public List<ExerciseSessionResponse> getExercisesAtDay(String date) {
        return sessionRepository.findAllByDate(Date.valueOf(date)).stream()
                .map(exerciseMapper::toResponse)
                .collect(Collectors.toList());
    }

    public void addExercise(ExerciseSessionRequest request) {
        ExerciseSessionEntity session = new ExerciseSessionEntity();


        // TODO: get user from security context
        UUID userId = UUID.fromString("286ace31-3bb9-4749-b36f-91b203e0c759");

        session.setUserId(userId);
        session.setTemplateId(request.templateId());
        session.setMetricAmount(request.metricAmount());
        session.setDate(new Date(request.date().getTime()));
        sessionRepository.save(session);
    }

    public void updateExercise(UUID id, ExerciseSessionRequest request) {
        ExerciseSessionEntity session = sessionRepository.findById(id).orElseThrow(() -> new RuntimeException("Session not found"));
        session.setMetricAmount(request.metricAmount());
        session.setDate(new Date(request.date().getTime()));
        sessionRepository.save(session);
    }

    public void deleteExercise(UUID id) {
        sessionRepository.deleteById(id);
    }
}
