package com.microservices.categoria_service.controller;

import com.microservices.categoria_service.model.Categoria;
import com.microservices.categoria_service.repository.CategoriaRepository;
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

import java.util.List;

@RestController
@RequestMapping("/api/categorias")  // AGREGAR "/" al inicio
@Tag(name = "Categorías", description = "API REST para gestión de categorías")
public class CategoriaController {

    private final CategoriaRepository categoriaRepository;

    public CategoriaController(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Operation(
            summary = "Listar todas las categorías",
            description = "Obtiene una lista completa de todas las categorías registradas"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de categorías obtenida exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Categoria.class)
                    )
            )
    })
    @GetMapping
    public List<Categoria> Listar() {
        return categoriaRepository.findAll();
    }

    @Operation(
            summary = "Obtener categoría por ID",
            description = "Busca y retorna una categoría específica mediante su identificador único"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Categoría encontrada exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Categoria.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Categoría no encontrada"
            )
    })
    @GetMapping("/{id}")  // AGREGAR esta anotación que faltaba
    public ResponseEntity<Categoria> obtenerPorId(
            @Parameter(description = "ID de la categoría a buscar", required = true, example = "1")
            @PathVariable Long id
    ) {
        return categoriaRepository.findById(id)
                .map(ResponseEntity::ok)  // Si existe -> 200 (no 250)
                .orElse(ResponseEntity.notFound().build()); // Si no -> 404
    }

    @Operation(
            summary = "Crear nueva categoría",
            description = "Registra una nueva categoría en el sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Categoría creada exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Categoria.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos inválidos proporcionados"
            )
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)  // AGREGAR para retornar 201
    public Categoria crear(
            @Parameter(description = "Datos de la categoría a crear", required = true)
            @RequestBody Categoria categoria
    ) {
        return categoriaRepository.save(categoria);
    }

    @Operation(
            summary = "Actualizar categoría existente",
            description = "Modifica los datos de una categoría existente en el sistema"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Categoría actualizada exitosamente",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Categoria.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Categoría no encontrada"
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> actualizar(
            @Parameter(description = "ID de la categoría a actualizar", required = true, example = "1")
            @PathVariable Long id,
            @Parameter(description = "Nuevos datos de la categoría", required = true)
            @RequestBody Categoria datosCategoria
    ) {
        return categoriaRepository.findById(id)
                .map(categoriaExistente -> {
                    categoriaExistente.setNombre(datosCategoria.getNombre());
                    return ResponseEntity.ok(categoriaRepository.save(categoriaExistente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(
            summary = "Eliminar categoría",
            description = "Elimina una categoría del sistema de forma permanente"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204",
                    description = "Categoría eliminada exitosamente"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Categoría no encontrada"
            )
    })
    @DeleteMapping("/{id}")  // AGREGAR esta anotación que faltaba
    public ResponseEntity<Void> eliminar(  // CAMBIAR tipo de retorno a Void
                                           @Parameter(description = "ID de la categoría a eliminar", required = true, example = "1")
                                           @PathVariable Long id
    ) {
        if(!categoriaRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        categoriaRepository.deleteById(id);
        return ResponseEntity.noContent().build();  // 204 No Content
    }
}