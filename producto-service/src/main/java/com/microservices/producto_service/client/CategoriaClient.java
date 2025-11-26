package com.microservices.producto_service.client;

import com.microservices.producto_service.dto.CategoriaDTO;  // Cambiar import
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "categoria-service")
public interface CategoriaClient {
    @GetMapping("/api/categorias/{id}")
    CategoriaDTO getCategoriaById(@PathVariable Long id);  // Cambiar de Categoria a CategoriaDTO
}