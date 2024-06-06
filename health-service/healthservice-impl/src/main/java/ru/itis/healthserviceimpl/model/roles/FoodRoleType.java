package ru.itis.healthserviceimpl.model.roles;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

public enum FoodRoleType implements Role {
    VIEWER, REPORTER, EDITOR, SUPER_VIEWER;

    private final Set<Role> children = new HashSet<>();

    static {
        REPORTER.children.add(VIEWER);
        EDITOR.children.add(VIEWER);
        SUPER_VIEWER.children.add(VIEWER);
    }

    @Override
    public boolean isIncludes(Role role) {
        return this.equals(role) || children.stream().anyMatch(l -> l.isIncludes(role));
    }

    @Component("FoodRoleType")
    @Getter
    static class SpringComponent {
        private final FoodRoleType VIEWER = FoodRoleType.VIEWER;
        private final FoodRoleType EDITOR = FoodRoleType.EDITOR;
        private final FoodRoleType REPORTER = FoodRoleType.REPORTER;
    }
}
