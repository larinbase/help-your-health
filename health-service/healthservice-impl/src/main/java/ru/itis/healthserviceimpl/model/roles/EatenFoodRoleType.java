package ru.itis.healthserviceimpl.model.roles;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.healthserviceapi.api.DrinkingWaterApi;
import ru.itis.healthserviceapi.dto.request.DrinkingWaterRequest;
import ru.itis.healthserviceapi.dto.response.DrinkingWaterResponse;
import ru.itis.healthserviceimpl.service.DrinkingWaterService;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public enum EatenFoodRoleType implements Role {
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

    @Component("EatenFoodRoleType")
    @Getter
    static class SpringComponent {
        private final EatenFoodRoleType VIEWER = EatenFoodRoleType.VIEWER;
        private final EatenFoodRoleType EDITOR = EatenFoodRoleType.EDITOR;
        private final EatenFoodRoleType REPORTER = EatenFoodRoleType.REPORTER;
        private final EatenFoodRoleType SUPER_VIEWER = EatenFoodRoleType.SUPER_VIEWER;
    }
}
