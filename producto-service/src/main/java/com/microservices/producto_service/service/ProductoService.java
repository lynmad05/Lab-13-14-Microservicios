package com.microservices.producto_service.service;

import com.microservices.producto_service.client.CategoriaClient;
import com.microservices.producto_service.dto.CategoriaDTO;
import com.microservices.producto_service.dto.ProductResponse;
import com.microservices.producto_service.model.Producto;
import com.microservices.producto_service.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaClient categoriaClient;

    public List<ProductResponse> getAllProductos() {
        List<Producto> productos = productoRepository.findAll();
        return productos.stream()
                .map(this::mapToProductResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse getProductoById(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
        return mapToProductResponse(producto);
    }

    public Producto createProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    public Producto updateProducto(Long id, Producto productoDetails) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

        producto.setNombre(productoDetails.getNombre());
        producto.setPrecio(productoDetails.getPrecio());
        producto.setCategoriaId(productoDetails.getCategoriaId());

        return productoRepository.save(producto);
    }

    public void deleteProducto(Long id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
        productoRepository.delete(producto);
    }

    private ProductResponse mapToProductResponse(Producto producto) {
        ProductResponse response = new ProductResponse();
        response.setProducto(producto);

        if (producto.getCategoriaId() != null) {
            try {
                CategoriaDTO categoria = categoriaClient.getCategoriaById(producto.getCategoriaId());
                response.setCategoria(categoria);
            } catch (Exception e) {
                System.out.println("No se pudo obtener la categoría: " + e.getMessage());
            }
        }

        return response;
    }
}