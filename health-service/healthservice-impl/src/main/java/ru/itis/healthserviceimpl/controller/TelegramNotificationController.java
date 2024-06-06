package ru.itis.healthserviceimpl.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.healthserviceapi.api.TelegramNotificationApi;
import ru.itis.healthserviceimpl.service.TelegramNotificationService;

@RestController
@RequiredArgsConstructor
public class TelegramNotificationController implements TelegramNotificationApi {

	private final TelegramNotificationService telegramService;

	@Override
	public void subscribe(String nickname) {
		telegramService.subscribe(nickname);
	}

	@Override
	public void unsubscribe(String nickname) {
		telegramService.unsubscribe(nickname);
	}

}
