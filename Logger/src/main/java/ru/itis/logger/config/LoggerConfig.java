package ru.itis.logger.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import ru.itis.logger.aspect.LoggerAspect;


@Configuration
@ComponentScan("ru.itis")
@ConditionalOnProperty(prefix = "finance.logger", value = "enabled", havingValue = "true", matchIfMissing = true)
public class LoggerConfig {

    @Bean
    public LoggerAspect loggerAspect() {
        return new LoggerAspect();
    }

}
