package ru.itis.healthserviceimpl.model.roletype;

import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

public enum RecipeRoleType implements Role {
    VIEWER, EDITOR, REPORTER;

    private final Set<Role> children = new HashSet<>();

    static {
        REPORTER.children.add(VIEWER);
        EDITOR.children.add(VIEWER);
    }

    @Override
    public boolean isIncludes(Role role) {
        return this.equals(role) || children.stream().anyMatch(l -> l.isIncludes(role));
    }

    @Component("RecipeRoleType")
    @Getter
    static class SpringComponent {
        private final RecipeRoleType VIEWER = RecipeRoleType.VIEWER;
        private final RecipeRoleType EDITOR = RecipeRoleType.EDITOR;
        private final RecipeRoleType REPORTER = RecipeRoleType.REPORTER;
    }
}
