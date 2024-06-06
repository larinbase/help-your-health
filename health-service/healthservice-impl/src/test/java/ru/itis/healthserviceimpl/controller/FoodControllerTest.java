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
import ru.itis.healthserviceapi.dto.request.FoodRequest;
import ru.itis.healthserviceapi.dto.response.FoodResponse;
import ru.itis.healthserviceimpl.service.FoodService;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(FoodController.class)
@WithMockUser
public class FoodControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private FoodService service;

	private final String TOKEN_ATTR_NAME =
			"org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository.CSRF_TOKEN";
	private final HttpSessionCsrfTokenRepository httpSessionCsrfTokenRepository = new HttpSessionCsrfTokenRepository();
	private final CsrfToken csrfToken = httpSessionCsrfTokenRepository.generateToken(new MockHttpServletRequest());

	private final UUID id = UUID.randomUUID();
	private final Instant createDate = Instant.now();
	private final Instant updateDate = Instant.now();

	@Test
	void shouldSaveFood() throws Exception {
		Mockito.when(service.save(Mockito.any(FoodRequest.class))).thenReturn(id);

		mockMvc.perform(post("/api/v1/foods")
						.with(csrf())
						.sessionAttr(TOKEN_ATTR_NAME, csrfToken)
						.param(csrfToken.getParameterName(), csrfToken.getToken())
						.contentType(MediaType.APPLICATION_JSON)
						.content(getRequest()))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$").value(id.toString()));
	}

	@Test
	void shouldReturnFoodById() throws Exception {
		Mockito.when(service.getById(id)).thenReturn(getFoodResponse());

		mockMvc.perform(get("/api/v1/foods/{id}", id))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(id.toString()))
				.andExpect(jsonPath("$.createdDate").value(createDate.toString()))
				.andExpect(jsonPath("$.updatedDate").value(updateDate.toString()))
				.andExpect(jsonPath("$.name").value("Food"))
				.andExpect(jsonPath("$.proteins").value(10))
				.andExpect(jsonPath("$.fats").value(5))
				.andExpect(jsonPath("$.carbohydrates").value(15))
				.andExpect(jsonPath("$.caloriesNumber").value(200))
				.andExpect(jsonPath("$.typeOfFood").value(1))
				.andExpect(jsonPath("$.categoryId").value(id.toString()));
	}

	@Test
	void shouldReturnAllFoods() throws Exception {
		Mockito.when(service.getAll()).thenReturn(Set.of(getFoodResponse()));

		mockMvc.perform(get("/api/v1/foods"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.length()").value(1))
				.andExpect(jsonPath("$[0].id").value(id.toString()))
				.andExpect(jsonPath("$[0].createdDate").value(createDate.toString()))
				.andExpect(jsonPath("$[0].updatedDate").value(updateDate.toString()))
				.andExpect(jsonPath("$[0].name").value("Food"))
				.andExpect(jsonPath("$[0].proteins").value(10))
				.andExpect(jsonPath("$[0].fats").value(5))
				.andExpect(jsonPath("$[0].carbohydrates").value(15))
				.andExpect(jsonPath("$[0].caloriesNumber").value(200))
				.andExpect(jsonPath("$[0].typeOfFood").value(1))
				.andExpect(jsonPath("$[0].categoryId").value(id.toString()));
	}

	@Test
	void shouldUpdateFood() throws Exception {
		mockMvc.perform(put("/api/v1/foods/{id}", id)
						.with(csrf())
						.sessionAttr(TOKEN_ATTR_NAME, csrfToken)
						.param(csrfToken.getParameterName(), csrfToken.getToken())
						.contentType(MediaType.APPLICATION_JSON)
						.content(getRequest()))
				.andExpect(status().isNoContent());
	}

	@Test
	void shouldDeleteFood() throws Exception {
		mockMvc.perform(delete("/api/v1/foods/{id}", id)
						.with(csrf())
						.sessionAttr(TOKEN_ATTR_NAME, csrfToken)
						.param(csrfToken.getParameterName(), csrfToken.getToken()))
				.andExpect(status().isNoContent());
	}

	private FoodResponse getFoodResponse() {
		return new FoodResponse(id, createDate, updateDate, "Food", (short) 10, (short) 5, (short) 15, (short) 200, (short) 1, id);
	}

	private String getRequest() throws JsonProcessingException {
		FoodRequest request = new FoodRequest("Food", (short) 10, (short) 5, (short) 15, (short) 200, (short) 1, id);

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(request);
	}
}
