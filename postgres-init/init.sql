-- =============================
-- Inicialización de categorias_db
-- =============================
-- Conectar a categorias_db (esta se crea con POSTGRES_DB)
\c categorias_db

-- Crear tabla categoria
CREATE TABLE IF NOT EXISTS categoria (
                                         id SERIAL PRIMARY KEY,
                                         nombre VARCHAR(255) NOT NULL
    );

-- Insertar datos de ejemplo
INSERT INTO categoria (nombre) VALUES ('Electrónica');
INSERT INTO categoria (nombre) VALUES ('Ropa');
INSERT INTO categoria (nombre) VALUES ('Alimentos');

-- =============================
-- Inicialización de productos_db
-- =============================
-- Crear base de datos productos_db si no existe
-- Nota: CREATE DATABASE no funciona dentro de la misma sesión si ya estás conectado
-- Por eso, dejamos que Docker la cree vía variable POSTGRES_DB
-- Si necesitas crearla aquí, debes ejecutar en psql aparte:
-- CREATE DATABASE productos_db;

-- Conectar a productos_db
\c productos_db

-- Crear tabla producto
CREATE TABLE IF NOT EXISTS producto (
                                        id SERIAL PRIMARY KEY,
                                        nombre VARCHAR(255) NOT NULL,
    precio DOUBLE PRECISION NOT NULL,
    categoria_id BIGINT
    );
