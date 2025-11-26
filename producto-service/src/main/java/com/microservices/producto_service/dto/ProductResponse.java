package com.microservices.producto_service.dto;

import com.microservices.producto_service.model.Producto;
import lombok.Data;

@Data
public class ProductResponse {

    private Producto producto;
    private CategoriaDTO categoria;  // Cambiar de CategoriaClient a CategoriaDTO
}

