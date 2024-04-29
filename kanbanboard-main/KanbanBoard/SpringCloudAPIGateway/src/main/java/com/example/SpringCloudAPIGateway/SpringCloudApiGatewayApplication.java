package com.example.SpringCloudAPIGateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringCloudApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCloudApiGatewayApplication.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/api/v2/**")
                        .uri("http://localhost:8083/"))

                .route(p -> p.path("/api/v1/**")
                        .uri("http://localhost:8084/")).build();
    }
}
