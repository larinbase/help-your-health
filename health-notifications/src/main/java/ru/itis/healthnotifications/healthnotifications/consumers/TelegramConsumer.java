package ru.itis.healthnotifications.healthnotifications.consumers;

import lombok.extern.log4j.Log4j2;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class TelegramConsumer {

	@RabbitListener(queues = "water-notify")
	public void telegramHandler (String message) {



	}

}
