package ru.itis.healthserviceimpl.mapper;

import org.mapstruct.Mapper;
import ru.itis.healthserviceapi.dto.request.NutritionalInfoRequest;
import ru.itis.healthserviceapi.dto.response.NutritionalInfoResponse;
import ru.itis.healthserviceimpl.model.additionalinfo.NutritionalInfo;

@Mapper(componentModel = "spring")
public interface NutritionalInfoMapper {

    NutritionalInfo toEntity(NutritionalInfoRequest request);

    NutritionalInfoResponse toResponse(NutritionalInfo nutritionalInfo);
}
