package ru.itis.healthserviceimpl.model.roletype;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

public enum DrinkingWaterRoleType implements Role {
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

    @Component("DrinkingWaterRoleType")
    @Getter
    static class SpringComponent {
        private final DrinkingWaterRoleType VIEWER = DrinkingWaterRoleType.VIEWER;
        private final DrinkingWaterRoleType EDITOR = DrinkingWaterRoleType.EDITOR;
        private final DrinkingWaterRoleType REPORTER = DrinkingWaterRoleType.REPORTER;
        private final DrinkingWaterRoleType SUPER_VIEWER = DrinkingWaterRoleType.SUPER_VIEWER;
    }
}
