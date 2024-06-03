package ru.itis.healthauthimpl.mapper;

import org.mapstruct.Mapper;
import ru.itis.healthauthapi.dto.UserResponse;
import ru.itis.healthauthimpl.model.UserEntity;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponse toUserResponse(UserEntity userEntity);
}
