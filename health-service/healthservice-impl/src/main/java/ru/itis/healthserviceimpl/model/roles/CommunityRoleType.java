package ru.itis.healthserviceimpl.model.roles;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public enum CommunityRoleType implements Role {
    ADMIN, MODERATOR, PRIVILEGED_USER, USER;

    private final Set<Role> children = new HashSet<>();

    static {
        USER.children.addAll(List.of(
                RecipeRoleType.VIEWER,
                RecipeRoleType.EDITOR,
                RecipeRoleType.REPORTER,
                TrainingRoleType.VIEWER,
                TrainingRoleType.REPORTER)
        );
        PRIVILEGED_USER.children.add(USER);
        MODERATOR.children.addAll(List.of(PRIVILEGED_USER, TrainingRoleType.EDITOR));
        ADMIN.children.add(MODERATOR);
    }

    @Override
    public boolean isIncludes(Role role) {
        return this.equals(role) || children.stream().anyMatch(this::isIncludes);
    }
}
