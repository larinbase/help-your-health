package ru.itis.healthserviceimpl.mapper;

import org.mapstruct.Mapper;
import ru.itis.healthserviceapi.dto.request.UserSave;
import ru.itis.healthserviceapi.dto.request.UserUpdate;
import ru.itis.healthserviceapi.dto.response.UserResponse;
import ru.itis.healthserviceimpl.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User fromRequest(UserSave userSave);

    User fromRequest(UserUpdate userUpdate);

    UserResponse toResponse(User user);
}
