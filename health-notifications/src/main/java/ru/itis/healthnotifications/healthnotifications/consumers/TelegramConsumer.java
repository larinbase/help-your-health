package ru.itis.healthnotifications.healthnotifications.consumers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.itis.healthnotifications.healthnotifications.notifications.TelegramService;

@Log4j2
@Component
@RequiredArgsConstructor
public class TelegramConsumer {

	private final TelegramService telegramService;

	@RabbitListener(queues = "water-notify")
	public void telegramHandler (String message) {

		if (message.equals("parse")) {
			telegramService.parsing();
		}

		if (message.equals("send")) {
			telegramService.sendNotification();
		}

	}

}

