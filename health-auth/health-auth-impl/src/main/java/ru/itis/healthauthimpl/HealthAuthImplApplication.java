package ru.itis.healthauthimpl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class HealthAuthImplApplication {

    public static void main(String[] args) {
        SpringApplication.run(HealthAuthImplApplication.class, args);
    }

}
