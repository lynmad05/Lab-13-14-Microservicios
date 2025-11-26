package com.microservices.producto_service.controller;

import com.microservices.producto_service.dto.ProductResponse;
import com.microservices.producto_service.model.Producto;
import com.microservices.producto_service.service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
@Tag(name = "Productos", description = "API REST para gestión de productos")
public class ProductoController {

    private final ProductoService productoService;

    @Operation(
            summary = "Listar todos los productos",
            description = "Obtiene una lista completa de todos los productos con información de sus categorías"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de productos obtenida exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor"
            )
    })
    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProductos() {
        List<ProductResponse> productos = productoService.getAllProductos();
        return ResponseEntity.ok(productos);
    }

    @Operation(
            summary = "Obtener producto por ID",
            description = "Busca y retorna un producto específico mediante su identificador único"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Producto encontrado exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ProductResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Producto no encontrado"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductoById(
            @Parameter(description = "ID del producto a buscar", required = true, example = "1")
            @PathVariable Long id
    ) {
        ProductResponse producto = productoService.getProductoById(id);
        return ResponseEntity.ok(producto);
    }

    @Operation(
            summary = "Crear nuevo producto",
            description = "Registra un nuevo producto en el sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Producto creado exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Producto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos proporcionados"
            )
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Producto> createProducto(
            @Parameter(description = "Datos del producto a crear", required = true)
            @RequestBody Producto producto
    ) {
        Producto nuevoProducto = productoService.createProducto(producto);
        return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
    }

    @Operation(
            summary = "Actualizar producto existente",
            description = "Modifica los datos de un producto existente en el sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Producto actualizado exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Producto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Producto no encontrado"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos proporcionados"
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Producto> updateProducto(
            @Parameter(description = "ID del producto a actualizar", required = true, example = "1")
            @PathVariable Long id,
            @Parameter(description = "Nuevos datos del producto", required = true)
            @RequestBody Producto producto
    ) {
        Producto productoActualizado = productoService.updateProducto(id, producto);
        return ResponseEntity.ok(productoActualizado);
    }

    @Operation(
            summary = "Eliminar producto",
            description = "Elimina un producto del sistema de forma permanente"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Producto eliminado exitosamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Producto no encontrado"
            )
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> deleteProducto(
            @Parameter(description = "ID del producto a eliminar", required = true, example = "1")
            @PathVariable Long id
    ) {
        productoService.deleteProducto(id);
        return ResponseEntity.noContent().build();
    }
}