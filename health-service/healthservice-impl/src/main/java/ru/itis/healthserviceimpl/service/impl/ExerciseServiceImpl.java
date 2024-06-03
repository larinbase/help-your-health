package ru.itis.healthserviceimpl.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.healthserviceapi.dto.request.ExerciseSessionRequest;
import ru.itis.healthserviceapi.dto.request.ExerciseTemplateRequest;
import ru.itis.healthserviceapi.dto.response.ExerciseSessionResponse;
import ru.itis.healthserviceapi.dto.response.ExerciseTemplateResponse;
import ru.itis.healthserviceimpl.exception.NotFoundException;
import ru.itis.healthserviceimpl.exception.UserNotFoundException;
import ru.itis.healthserviceimpl.mapper.ExerciseMapper;
import ru.itis.healthserviceimpl.model.ExerciseSessionEntity;
import ru.itis.healthserviceimpl.model.ExerciseSessionRole;
import ru.itis.healthserviceimpl.model.ExerciseTemplateEntity;
import ru.itis.healthserviceimpl.model.User;
import ru.itis.healthserviceimpl.repository.ExerciseSessionRepository;
import ru.itis.healthserviceimpl.repository.ExerciseTemplateRepository;
import ru.itis.healthserviceimpl.repository.UserRepository;
import ru.itis.healthserviceimpl.security.userdetails.BaseUserDetails;
import ru.itis.healthserviceimpl.service.ExerciseService;
import ru.itis.healthserviceimpl.service.ExerciseSessionRoleService;

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
    private final ExerciseSessionRoleService roleService;
    private final UserRepository userRepository;

    @Override
    public Page<ExerciseTemplateResponse> getTemplates(Pageable pageable) {
        return templateRepository.findAll(pageable).map(exerciseMapper::toResponse);
    }

    @Override
    public List<ExerciseTemplateResponse> searchTemplates(String query) {
        return templateRepository.findAll().stream()
                .filter(template -> template.getDescription().toLowerCase().contains(query.toLowerCase()))
                .map(exerciseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UUID createTemplate(ExerciseTemplateRequest templateRequest) {
        ExerciseTemplateEntity template = new ExerciseTemplateEntity();
        template.setDescription(templateRequest.description());
        template.setImage(templateRequest.image());
        template.setCaloriesPerUnit(templateRequest.caloriesPerUnit());
        template.setMetricUnit(templateRequest.metricUnit());
        template.setCustom(templateRequest.isCustom());
        return templateRepository.save(template).getId();
    }

    @Override
    public void updateTemplate(UUID templateId, ExerciseTemplateRequest templateRequest) {
        ExerciseTemplateEntity template = templateRepository.findById(templateId)
                .orElseThrow(() -> new NotFoundException("Template not found"));
        template.setDescription(templateRequest.description());
        template.setImage(templateRequest.image());
        template.setCaloriesPerUnit(templateRequest.caloriesPerUnit());
        template.setMetricUnit(templateRequest.metricUnit());
        template.setCustom(templateRequest.isCustom());
        templateRepository.save(template);
    }

    @Override
    public void deleteTemplate(UUID templateId) {
        templateRepository.deleteById(templateId);
    }

    @Override
    public List<ExerciseSessionResponse> getExercisesAtDay(String date) {
        BaseUserDetails user = (BaseUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return sessionRepository.findAllByDateAndUserId(Date.valueOf(date), user.getId()).stream()
                .map(exerciseMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void addExercise(ExerciseSessionRequest request) {
        ExerciseSessionEntity session = new ExerciseSessionEntity();
        BaseUserDetails user = (BaseUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        session.setUserId(user.getId());
        session.setTemplateId(request.templateId());
        session.setMetricAmount(request.metricAmount());
        session.setDate(new Date(request.date().getTime()));
        session = sessionRepository.save(session);
        roleService.create(user.getId(), session.getId());
    }

    @Override
    public void updateExercise(UUID id, ExerciseSessionRequest request) {
        ExerciseSessionEntity session = sessionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Session not found"));
        session.setMetricAmount(request.metricAmount());
        session.setDate(new Date(request.date().getTime()));
        sessionRepository.save(session);
    }

    public void deleteExercise(UUID id) {
        sessionRepository.deleteById(id);
    }
}
