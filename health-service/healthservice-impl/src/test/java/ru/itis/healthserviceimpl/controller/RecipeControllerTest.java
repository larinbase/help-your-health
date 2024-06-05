package ru.itis.healthserviceimpl.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.healthserviceapi.dto.request.IngredientRequest;
import ru.itis.healthserviceapi.dto.request.NutritionalInfoRequest;
import ru.itis.healthserviceapi.dto.request.RecipeRequest;
import ru.itis.healthserviceapi.dto.response.IngredientResponse;
import ru.itis.healthserviceapi.dto.response.NutritionalInfoResponse;
import ru.itis.healthserviceapi.dto.response.RecipeResponse;
import ru.itis.healthserviceimpl.service.RecipeService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(RecipeController.class)
@WithMockUser
public class RecipeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecipeService service;

    private final String TOKEN_ATTR_NAME =
            "org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN";
    private final HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
    private final CsrfToken csrfToken = httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());

    private final UUID id = UUID.randomUUID();

    @Test
    void shouldCreateRecipe() throws Exception {
        mockMvc.perform(post("/api/v1/recipe")
                        .with(csrf())
                        .sessionAttr(TOKEN_ATTR_NAME, csrfToken)
                        .param(csrfToken.getParameterName(), csrfToken.getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getRequest()))
                .andExpect(status().isCreated());
    }

    @Test
    void shouldReturnAllRecipes() throws Exception {
        Mockito.when(service.findAll(0, 10)).thenReturn(getRecipes());

        mockMvc.perform(get("/api/v1/recipe"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(2));
    }

    @Test
    void shouldReturnById() throws Exception {
        Mockito.when(service.findById(id)).thenReturn(getRecipes().getContent().get(0));

        mockMvc.perform(get("/api/v1/recipe/findById/%s".formatted(id)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id.toString()))
                .andExpect(jsonPath("$.title").value("Вкусное блюдо"))
                .andExpect(jsonPath("$.cookingTime").value(30));
    }

    @Test
    void shouldReturnByTitle() throws Exception {
        String title = "блюдо";
        Mockito.when(service.findByTitle(title, 0, 10)).thenReturn(getRecipes());

        mockMvc.perform(get("/api/v1/recipe/findByTitle/%s".formatted(title)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(2));
    }

    @Test
    void shouldReturnByCategory() throws Exception {
        String category = "здоровое";
        Mockito.when(service.findByCategory(category, 0, 10)).thenReturn(getRecipes());

        mockMvc.perform(get("/api/v1/recipe/findByCategory/%s".formatted(category)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(2));
    }

    @Test
    void shouldReturnByCookingTime() throws Exception {
        int cookingTime = 30;
        Mockito.when(service.findByCookingTime(cookingTime, 0, 10)).thenReturn(new PageImpl<>(
                List.of(getRecipes().getContent().get(0)), PageRequest.of(0, 10), 1));

        mockMvc.perform(get("/api/v1/recipe/findByCookingTime/%d".formatted(cookingTime)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content.length()").value(1));
    }

    @Test
    void shouldUpdate() throws Exception {
        mockMvc.perform(put("/api/v1/recipe/%s".formatted(id))
                        .with(csrf())
                        .sessionAttr(TOKEN_ATTR_NAME, csrfToken)
                        .param(csrfToken.getParameterName(), csrfToken.getToken())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(getRequest()))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDelete() throws Exception {
        mockMvc.perform(delete("/api/v1/recipe/%s".formatted(id))
                        .with(csrf())
                        .sessionAttr(TOKEN_ATTR_NAME, csrfToken)
                        .param(csrfToken.getParameterName(), csrfToken.getToken()))
                .andExpect(status().isOk());
    }

    private Page<RecipeResponse> getRecipes() {
        List<RecipeResponse> recipes = new ArrayList<>();

        recipes.add(new RecipeResponse(
                id,
                UUID.randomUUID(),
                "Вкусное блюдо",
                List.of("здоровое", "вегетарианское"),
                30,
                List.of(
                        new IngredientResponse("Картофель", 200, "г."),
                        new IngredientResponse("Лук", 1, "шт."),
                        new IngredientResponse("Соль", 1, "ч. л.")
                ),
                List.of(
                        "Очистить и нарезать картофель и лук.",
                        "Разогреть масло в сковороде и добавить лук.",
                        "Добавить картофель и готовить до золотистого цвета.",
                        "Посолить и подавать горячим."
                ),
                List.of("dish_image1.jpg", "dish_image2.jpg"),
                new NutritionalInfoResponse(300, 15, 8, 20)
        ));

        recipes.add(new RecipeResponse(
                UUID.randomUUID(),
                UUID.randomUUID(),
                "Наивкуснейшее блюдо",
                List.of("здоровое", "мясное"),
                60,
                List.of(
                        new IngredientResponse("Картофель", 200, "г."),
                        new IngredientResponse("Лук", 1, "шт."),
                        new IngredientResponse("Говядина", 200, "г.")
                ),
                List.of(
                        "Очистить и нарезать картофель и лук.",
                        "Разогреть масло в сковороде и добавить лук.",
                        "Добавить картофель и готовить до золотистого цвета.",
                        "Посолить и подавать горячим."
                ),
                List.of("dish_image1.jpg", "dish_image2.jpg"),
                new NutritionalInfoResponse(300, 15, 8, 20)
        ));

        return new PageImpl<>(recipes, PageRequest.of(0, 10), recipes.size());
    }

    private String getRequest() throws JsonProcessingException {
        RecipeRequest recipe = new RecipeRequest("Картошка",
                List.of("Здоровое", "Низкокалорийное"),
                30,
                List.of(
                        new IngredientRequest("Картофель", 200, "г."),
                        new IngredientRequest("Лук", 1, "шт."),
                        new IngredientRequest("Соль", 1, "ч. л.")
                ),
                List.of(
                        "Очистить и нарезать картофель и лук.",
                        "Разогреть масло в сковороде и добавить лук.",
                        "Добавить картофель и готовить до золотистого цвета.",
                        "Посолить и подавать горячим."
                ),
                List.of("dish_image1.jpg", "dish_image2.jpg"),
                new NutritionalInfoRequest(300, 15, 8, 20));

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(recipe);
    }
}
