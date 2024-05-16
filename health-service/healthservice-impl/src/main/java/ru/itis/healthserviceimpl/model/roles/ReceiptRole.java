package ru.itis.healthserviceimpl.model.roles;

import java.util.HashSet;
import java.util.Set;

public enum ReceiptRole implements Role {
    VIEWER, EDITOR, REPORTER;

    private final Set<Role> children = new HashSet<>();

    static {
        REPORTER.children.add(VIEWER);
        EDITOR.children.add(VIEWER);
    }

    @Override
    public boolean isIncludes(Role role) {
        return this.equals(role) || children.stream().anyMatch(this::isIncludes);
    }
}
