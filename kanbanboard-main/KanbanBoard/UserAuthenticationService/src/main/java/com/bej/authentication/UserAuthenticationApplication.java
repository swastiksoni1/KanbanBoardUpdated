package com.bej.authentication;

import com.bej.authentication.controller.UserController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@EnableEurekaClient

public class UserAuthenticationApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserAuthenticationApplication.class, args);
    }

}
