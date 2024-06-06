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
import ru.itis.healthserviceapi.dto.request.FoodCategoryRequest;
import ru.itis.healthserviceapi.dto.response.FoodCategoryResponse;
import ru.itis.healthserviceimpl.service.FoodCategoryService;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(FoodCategoryController.class)
@WithMockUser
public class FoodCategoryControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FoodCategoryService service;

	private final String TOKEN_ATTR_NAME =
			"org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN";
	private final HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
	private final CsrfToken csrfToken = httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());

	private final UUID id = UUID.randomUUID();

	private final Instant createDate = Instant.now();
	private final Instant updateDate = Instant.now();

	@Test
	void shouldSaveFoodCategory() throws Exception {
		Mockito.when(service.save(Mockito.any(FoodCategoryRequest.class))).thenReturn(id);

		mockMvc.perform(post("/api/v1/food-categories")
						.with(csrf())
						.sessionAttr(TOKEN_ATTR_NAME, csrfToken)
						.param(csrfToken.getParameterName(), csrfToken.getToken())
						.contentType(MediaType.APPLICATION_JSON)
						.content(getRequest()))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$").value(id.toString()));
	}

	@Test
	void shouldReturnFoodCategoryById() throws Exception {
		Mockito.when(service.getById(id)).thenReturn(getFoodCategoryResponse());

		mockMvc.perform(get("/api/v1/food-categories/{id}", id))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(id.toString()))
				.andExpect(jsonPath("$.createdDate").value(createDate.toString()))
				.andExpect(jsonPath("$.updatedDate").value(updateDate.toString()))
				.andExpect(jsonPath("$.name").value("Здоровое питание"));
	}

	@Test
	void shouldReturnAllFoodCategories() throws Exception {
		Mockito.when(service.getAll()).thenReturn(Set.of(getFoodCategoryResponse()));

		mockMvc.perform(get("/api/v1/food-categories"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(1))
				.andExpect(jsonPath("$[0].id").value(id.toString()))
				.andExpect(jsonPath("$[0].createdDate").value(createDate.toString()))
				.andExpect(jsonPath("$[0].updatedDate").value(updateDate.toString()))
				.andExpect(jsonPath("$[0].name").value("Здоровое питание"));
	}

	@Test
	void shouldUpdateFoodCategory() throws Exception {
		mockMvc.perform(put("/api/v1/food-categories/{id}", id)
						.with(csrf())
						.sessionAttr(TOKEN_ATTR_NAME, csrfToken)
						.param(csrfToken.getParameterName(), csrfToken.getToken())
						.contentType(MediaType.APPLICATION_JSON)
						.content(getRequest()))
				.andExpect(status().isNoContent());
	}

	@Test
	void shouldDeleteFoodCategory() throws Exception {
		mockMvc.perform(delete("/api/v1/food-categories/{id}", id)
						.with(csrf())
						.sessionAttr(TOKEN_ATTR_NAME, csrfToken)
						.param(csrfToken.getParameterName(), csrfToken.getToken()))
				.andExpect(status().isNoContent());
	}

	private FoodCategoryResponse getFoodCategoryResponse() {
		return new FoodCategoryResponse(id, createDate, updateDate, "Здоровое питание");
	}

	private String getRequest() throws JsonProcessingException {
		FoodCategoryRequest request = new FoodCategoryRequest("Здоровое питание");

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(request);
	}
}
