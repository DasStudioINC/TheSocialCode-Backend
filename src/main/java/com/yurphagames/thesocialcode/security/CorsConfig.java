package com.yurphagames.thesocialcode.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            public void addCorsRegistry(CorsRegistry registry) {
                registry.addMapping("/**") // Apply to all /api/v1/... routes
                        .allowedOrigins("http://127.0.0.1:5500", "http://localhost:5500") // Allow your Live Server
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*");
            }
        };
    }
}