package ru.itis.healthserviceimpl.model.roletype;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
public enum CommunityRoleType implements Role {
    ADMIN, MODERATOR, PRIVILEGED_USER, USER;

    private final Set<Role> children = new HashSet<>();

    static {
        USER.children.addAll(List.of(
                RecipeRoleType.REPORTER,
                ExerciseTemplateRoleType.REPORTER,
                ExerciseSessionRoleType.REPORTER,
                DrinkingWaterRoleType.REPORTER,
                EatenFoodRoleType.REPORTER,
                FoodRoleType.REPORTER)
        );
        PRIVILEGED_USER.children.add(USER);
        MODERATOR.children.addAll(List.of(
                PRIVILEGED_USER,
                ExerciseTemplateRoleType.EDITOR,
                RecipeRoleType.EDITOR,
                DrinkingWaterRoleType.SUPER_VIEWER,
                DrinkingWaterRoleType.EDITOR,
                ExerciseSessionRoleType.EDITOR,
                EatenFoodRoleType.EDITOR,
                FoodCategoryRoleType.EDITOR,
                FoodCategoryRoleType.REPORTER,
                FoodRoleType.EDITOR)
        );
        ADMIN.children.add(MODERATOR);
    }

    @Override
    public boolean isIncludes(Role role) {
        log.info("this: {}, param: {}", this, role);
        return this.equals(role) || children.stream().anyMatch(l -> l.isIncludes(role));
    }

    @Component("CommunityRoleType")
    @Getter
    static class SpringComponent1 {
        private final CommunityRoleType ADMIN = CommunityRoleType.ADMIN;
        private final CommunityRoleType MODERATOR = CommunityRoleType.MODERATOR;
        private final CommunityRoleType PRIVILEGED_USER = CommunityRoleType.PRIVILEGED_USER;
        private final CommunityRoleType USER = CommunityRoleType.USER;
    }
}
