package ru.itis.healthserviceimpl.config;

import com.rabbitmq.client.AMQP;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfiguration {

	private final String waterNotificationName = "water-notify";

	private final String baseExchangeName = "baseExchange";

	@Bean
	Exchange baseExchange() {
		return ExchangeBuilder.directExchange(baseExchangeName).durable(true).build();
	}

	@Bean
	Queue mwaterNotificationQueue() {
		return QueueBuilder.durable(waterNotificationName).build();
	}

	@Bean
	Binding binding() {
		return BindingBuilder.bind(mwaterNotificationQueue()).to(baseExchange()).with("telegram-rk").noargs();
	}

}
