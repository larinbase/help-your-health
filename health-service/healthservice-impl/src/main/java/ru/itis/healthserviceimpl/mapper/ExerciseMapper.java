package ru.itis.healthserviceimpl.mapper;

import org.mapstruct.Mapper;
import ru.itis.healthserviceapi.dto.request.ExerciseSessionRequest;
import ru.itis.healthserviceapi.dto.request.ExerciseTemplateRequest;
import ru.itis.healthserviceapi.dto.response.ExerciseSessionResponse;
import ru.itis.healthserviceapi.dto.response.ExerciseTemplateResponse;
import ru.itis.healthserviceimpl.model.ExerciseSessionEntity;
import ru.itis.healthserviceimpl.model.ExerciseTemplateEntity;

@Mapper(componentModel = "spring")
public interface ExerciseMapper {

    ExerciseSessionResponse toResponse(ExerciseSessionEntity entity);

    ExerciseSessionEntity toEntity(ExerciseSessionRequest request);

    ExerciseTemplateResponse toResponse(ExerciseTemplateEntity entity);

    ExerciseTemplateEntity toEntity(ExerciseTemplateRequest request);

}
