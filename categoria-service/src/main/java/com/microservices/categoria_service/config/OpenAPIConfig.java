package com.microservices.categoria_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI categoriaServiceAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Categoria Service API")
                        .description("Microservicio para gestión de categorías")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Tu Nombre")
                                .email("tu.email@example.com")));
    }
}