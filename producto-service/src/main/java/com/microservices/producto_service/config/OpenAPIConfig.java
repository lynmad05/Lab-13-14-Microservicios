package com.microservices.producto_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI productoServiceAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Producto Service API")
                        .description("Microservicio para la gestión de productos. " +
                                "Permite crear, listar, actualizar y eliminar productos, " +
                                "integrándose con el servicio de categorías mediante Feign Client.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Rodrigo Salva")
                                .email("rodrigodanielsalvasaccatoma@gmail.com")
                                .url("https://github.com/tuusuario"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8082")
                                .description("Servidor de Desarrollo - Producto Service"),
                        new Server()
                                .url("http://localhost:8080/producto")
                                .description("A través del API Gateway")
                ));
    }
}