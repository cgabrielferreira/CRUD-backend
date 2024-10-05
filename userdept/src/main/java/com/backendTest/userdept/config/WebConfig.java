package com.backendTest.userdept.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://127.0.0.1:5500")  // Permitir apenas essa origem
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Permitir métodos
                .allowedHeaders("*")  // Permitir todos os cabeçalhos
                .allowCredentials(true);  // Permitir envio de credenciais se necessário
    }
}
