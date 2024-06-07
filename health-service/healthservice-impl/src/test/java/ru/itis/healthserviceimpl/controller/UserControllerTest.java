package ru.itis.healthserviceimpl.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.healthserviceapi.dto.request.UserSave;
import ru.itis.healthserviceapi.dto.request.UserUpdate;
import ru.itis.healthserviceapi.dto.response.UserResponse;
import ru.itis.healthserviceimpl.model.roles.CommunityRoleType;
import ru.itis.healthserviceimpl.service.UserService;

import java.util.UUID;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@WithMockUser
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService service;

    private final String TOKEN_ATTR_NAME =
            "org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN";
    private final HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
    private final CsrfToken csrfToken = httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());
    private final UUID id = UUID.randomUUID();

    @Test
    void shouldCreateRecipe() throws Exception {
        mockMvc.perform(post("/api/v1/user")
                        .with(csrf())
                        .sessionAttr(TOKEN_ATTR_NAME, csrfToken)
                        .param(csrfToken.getParameterName(), csrfToken.getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getSaveRequest()))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldReturnByUsername() throws Exception {
        String username = "username";
        Mockito.when(service.findByUsername(username)).thenReturn(getUser());

        mockMvc.perform(get("/api/v1/user/%s".formatted(username)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value(username))
                .andExpect(jsonPath("$.age").value(19))
                .andExpect(jsonPath("$.height").value(180));
    }

    @Test
    void shouldUpdate() throws Exception {
        mockMvc.perform(put("/api/v1/user")
                        .with(csrf())
                        .sessionAttr(TOKEN_ATTR_NAME, csrfToken)
                        .param(csrfToken.getParameterName(), csrfToken.getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getUpdateRequest()))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDelete() throws Exception {
        mockMvc.perform(delete("/api/v1/user/%s".formatted(id))
                        .with(csrf())
                        .sessionAttr(TOKEN_ATTR_NAME, csrfToken)
                        .param(csrfToken.getParameterName(), csrfToken.getToken()))
                .andExpect(status().isOk());
    }

    private UserResponse getUser() {
        return new UserResponse(
                "username",
                "firsname",
                "lastname",
                "MALE",
                19,
                70,
                180,
                50,
                60,
                80,
                90,
                100,
                "MEDIUM"
        );
    }

    private String getSaveRequest() throws JsonProcessingException {
        UserSave userSave = new UserSave(
                "username",
                "password",
                "firstname",
                "lastname",
                "MALE",
                19,
                70,
                180,
                "MEDIUM"
        );

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(userSave);
    }

    private String getUpdateRequest() throws JsonProcessingException {
        UserUpdate userUpdate = new UserUpdate(
                "firstname",
                "lastname",
                19,
                "MALE",
                75,
                185,
                "MEDIUM"
        );

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(userUpdate);
    }
}
