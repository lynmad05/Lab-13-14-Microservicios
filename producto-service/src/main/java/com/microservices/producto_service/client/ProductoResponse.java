package com.microservices.producto_service.client;

import com.microservices.producto_service.model.Producto;
import lombok.Data;

@Data
public class ProductoResponse {

    private Producto producto;
    private CategoriaClient categoria;
}
