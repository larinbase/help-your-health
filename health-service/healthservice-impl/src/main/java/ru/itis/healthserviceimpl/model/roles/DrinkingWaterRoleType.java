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
