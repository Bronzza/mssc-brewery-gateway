package com.gateway.brewary_gateway.configurations;


import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Profile("!local-discovery")
@Configuration
public class LocalHostRouteConfig {

    @Bean
    public RouteLocator localHostLocator(RouteLocatorBuilder builder) {
        return builder
                .routes()
                .route(r -> r.path("/api/v1/beer*", "/api/v1/beer/*", "/api/v1/beerUps/*", "/api/v1/beerUps*")
                        .uri("http://localhost:8080")
                        .id("beer-service"))
                .route(r -> r.path("/api/v1/customers*", "/api/v1/customers/*", "/api/v1/customers/**")
                        .uri("http://localhost:8081")
                        .id("order-service"))
                .route(r -> r.path("/api/v1/beer/*/inventory","/api/v1/beer*/inventory")
                        .uri("http://localhost:8082")
                        .id("inventory-service"))
                .route(r -> r.path("/api/v1/inventory/*","/api/v1/inventory*")
                        .uri("http://localhost:8083")
                        .id("inventory-failover"))
                .build();
    }
}
