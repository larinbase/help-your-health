package ru.itis.healthserviceimpl.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import ru.itis.healthserviceapi.dto.request.UserSave;
import ru.itis.healthserviceapi.dto.request.UserUpdate;
import ru.itis.healthserviceapi.dto.response.UserResponse;
import ru.itis.healthserviceimpl.model.additionalinfo.ActivityCoefficient;
import ru.itis.healthserviceimpl.model.additionalinfo.Sex;
import ru.itis.healthserviceimpl.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "role", ignore = true)
    @Mapping(source = "sex", target = "sex", qualifiedByName = "mapSex")
    @Mapping(source = "activityCoefficient", target = "activityCoefficient",
            qualifiedByName = "mapActivityCoefficient")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "proteins", ignore = true)
    @Mapping(target = "fats", ignore = true)
    @Mapping(target = "carbohydrates", ignore = true)
    @Mapping(target = "calorieAllowance", ignore = true)
    @Mapping(target = "waterNorm", ignore = true)
    User fromRequest(UserSave userSave);

    @Mapping(source = "sex", target = "sex", qualifiedByName = "mapSex")
    @Mapping(source = "activityCoefficient", target = "activityCoefficient",
            qualifiedByName = "mapActivityCoefficient")
    @Mapping(target = "proteins", ignore = true)
    @Mapping(target = "fats", ignore = true)
    @Mapping(target = "carbohydrates", ignore = true)
    @Mapping(target = "calorieAllowance", ignore = true)
    @Mapping(target = "waterNorm", ignore = true)
    User fromRequest(UserUpdate userUpdate);

    @Mapping(source = "sex", target = "sex", qualifiedByName = "mapSex")
    @Mapping(source = "activityCoefficient", target = "activityCoefficient",
            qualifiedByName = "mapActivityCoefficient")
    UserResponse toResponse(User user);

    @Named("mapSex")
    default String mapSex(Sex sex) {
        return sex.name();
    }

    @Named("mapSex")
    default Sex mapSex(String sex) {
        return Sex.valueOf(sex);
    }

    @Named("mapActivityCoefficient")
    default String mapActivityCoefficient(ActivityCoefficient activityCoefficient) {
        return activityCoefficient.name();
    }

    @Named("mapActivityCoefficient")
    default ActivityCoefficient mapActivityCoefficient(String activityCoefficient) {
        return ActivityCoefficient.valueOf(activityCoefficient);
    }
}
