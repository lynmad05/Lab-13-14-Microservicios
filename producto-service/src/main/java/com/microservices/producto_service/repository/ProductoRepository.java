package com.microservices.producto_service.repository;

import com.microservices.producto_service.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductoRepository extends JpaRepository<Producto,Long> {
}
