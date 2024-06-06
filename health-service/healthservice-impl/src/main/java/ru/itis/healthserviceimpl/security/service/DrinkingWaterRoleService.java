package ru.itis.healthserviceimpl.security.service;


import ru.itis.healthserviceimpl.model.roletype.Role;

import java.util.UUID;

public interface DrinkingWaterRoleService {
    boolean hasAnyRoleByDrinkingWaterId(UUID drinkingWaterId, Role... roles);
}
