package ru.itis.healthserviceimpl.model.roles;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public enum CommunityRole implements Role {
    ADMIN, MODERATOR, PRIVILEGED_USER, USER;

    private final Set<Role> children = new HashSet<>();

    static {
        USER.children.addAll(List.of(
                ReceiptRole.VIEWER,
                ReceiptRole.EDITOR,
                ReceiptRole.REPORTER,
                TrainingRole.VIEWER,
                TrainingRole.REPORTER)
        );
        PRIVILEGED_USER.children.add(USER);
        MODERATOR.children.addAll(List.of(PRIVILEGED_USER, TrainingRole.EDITOR));
        ADMIN.children.add(MODERATOR);
    }

    @Override
    public boolean isIncludes(Role role) {
        return this.equals(role) || children.stream().anyMatch(this::isIncludes);
    }
}
