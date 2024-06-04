package ru.itis.healthserviceimpl.scheduling;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
@RequiredArgsConstructor
public class TelegramNotificationSendler {

	private final RabbitTemplate rabbitTemplate;

	private final String baseExchange = "baseExchange";

	private final String waterNotificationRK = "telegram-rk";

	// Метод будет выполняться каждые 2 часа
	@Scheduled(fixedRate = 6000)
	private void sendWaterNotifications() {
		rabbitTemplate.convertAndSend(baseExchange, waterNotificationRK, "send");
	}

	// Метод будет выполняться каждые 2 часа
	@Scheduled(fixedRate = 6000)
	private void parseTelegramSubscribers() {
		rabbitTemplate.convertAndSend(baseExchange, waterNotificationRK, "parse");
	}

}
