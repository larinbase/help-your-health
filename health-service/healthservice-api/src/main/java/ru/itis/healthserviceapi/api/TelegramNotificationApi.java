package ru.itis.healthserviceapi.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.itis.healthserviceapi.dto.request.RecipeRequest;

@Tags(value = {
		@Tag(name = "Telegram notifications")
})
@Schema(description = "Обработка подключения уведомлений")
@RequestMapping("/api/v1/tg")
public interface TelegramNotificationApi {

	@Operation(summary = "Подписка на телеграмм уведомления о воде")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Пользовтель успешно подписался на уведомления"),
			@ApiResponse(responseCode = "400", description = "Ошибка валидации"),
			@ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
			@ApiResponse(responseCode = "403", description = "Не достаточно прав"),
			@ApiResponse(responseCode = "500", description = "Ведутся технические работы")
	})
	@PostMapping("/subscribe/{nickname}")
	void subscribe(@PathVariable("nickname") String nickname);

	@Operation(summary = "Отписка от телеграмм уведомления о воде")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Пользовтель успешно отписался на уведомления"),
			@ApiResponse(responseCode = "400", description = "Ошибка валидации"),
			@ApiResponse(responseCode = "401", description = "Не пройдена авторизация"),
			@ApiResponse(responseCode = "403", description = "Не достаточно прав"),
			@ApiResponse(responseCode = "500", description = "Ведутся технические работы")
	})
	@PostMapping("/unsubscribe/{nickname}")
	void unsubscribe(@PathVariable("nickname") String nickname);

}
