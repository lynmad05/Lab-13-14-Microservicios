-- Crear base de datos para productos si no existe
CREATE DATABASE productos_db;

-- Conectar a categorias_db
\c categorias_db;

-- Crear tabla categorias si no existe
CREATE TABLE IF NOT EXISTS categoria (
                                         id SERIAL PRIMARY KEY,
                                         nombre VARCHAR(255) NOT NULL
    );

-- Datos de ejemplo
INSERT INTO categoria (nombre) VALUES ('Electrónica') ON CONFLICT DO NOTHING;
INSERT INTO categoria (nombre) VALUES ('Ropa') ON CONFLICT DO NOTHING;
INSERT INTO categoria (nombre) VALUES ('Alimentos') ON CONFLICT DO NOTHING;

-- Conectar a productos_db
\c productos_db;

-- Crear tabla productos si no existe
CREATE TABLE IF NOT EXISTS producto (
                                        id SERIAL PRIMARY KEY,
                                        nombre VARCHAR(255) NOT NULL,
    precio DOUBLE PRECISION NOT NULL,
    categoria_id BIGINT
    );