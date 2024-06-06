package ru.itis.healthserviceimpl.model.roles;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

public enum FoodCategoryRoleType implements Role {
    VIEWER, REPORTER, EDITOR;

    private final Set<Role> children = new HashSet<>();

    static {
        REPORTER.children.add(VIEWER);
        EDITOR.children.add(VIEWER);
    }

    @Override
    public boolean isIncludes(Role role) {
        return this.equals(role) || children.stream().anyMatch(l -> l.isIncludes(role));
    }

    @Component("FoodCategoryRoleType")
    @Getter
    static class SpringComponent {
        private final FoodCategoryRoleType VIEWER = FoodCategoryRoleType.VIEWER;
        private final FoodCategoryRoleType EDITOR = FoodCategoryRoleType.EDITOR;
        private final FoodCategoryRoleType REPORTER = FoodCategoryRoleType.REPORTER;
    }
}
