
# EUREKA

![Eureka](https://github.com/user-attachments/assets/e6c40790-9e1e-4c9d-8d4d-704a665b650d)

Este proyecto implementa un **sistema de microservicios** utilizando **Spring Boot** y sigue el patrón de arquitectura de microservicios con **Eureka Server** y **API Gateway**.

---

## CATEGORÍA

![Categoría](https://github.com/user-attachments/assets/286a0400-9397-4016-bbfa-194d732d714b)

Microservicio dedicado a la gestión de categorías.

- **Clase principal:** `CategoriaApplication`
- **Interface:** `CategoriaRepository`
- **Controlador:** `CategoriaController`
- **Configuración:** `application.properties`

---

## PRODUCTOS

![Productos](https://github.com/user-attachments/assets/8fb4280a-3ce7-4db4-b859-5fa3873c8275)

Microservicio para el manejo de productos y su relación con categorías.

- **Clase principal:** `ProductoApplication`
- **Interface:** `ProductoRepository`
- **Servicios:** `ProductoService`, `ProductoController`
- **DTOs:** `ProductResponse`, `CategoriaClient` (para comunicación con Categorías)
- **Configuración:** `application.properties`
- **Pruebas:** Postman

---

## SERVICIOS

![Servicios](https://github.com/user-attachments/assets/fc911879-a240-43d8-acde-f22a8930492e)

Incluye los microservicios principales del proyecto:

- **Servidor Eureka:** Registro y descubrimiento de microservicios.  
- **API Gateway:** Punto de entrada unificado.  
- **Categorías:** Gestión de categorías.  
- **Productos:** Gestión de productos y comunicación con categorías.  

---

## Estructura del proyecto

microservices-app/
│
├── eureka-server/
├── api-gateway/
├── categorias-service/
└── productos-service/


---

## Tecnologías

- Java 17
- Spring Boot
- Spring Cloud (Eureka, Gateway, Feign)
- Maven
- Postman (para pruebas)

---

## Cómo ejecutar

1. Ejecutar **Eureka Server**.
2. Ejecutar **API Gateway**.
3. Ejecutar **Servicio de Categorías**.
4. Ejecutar **Servicio de Productos**.
5. Realizar pruebas con **Postman**.

---

## Conclusiones

1. Aprendí a implementar microservicios con Spring Boot.  
2. Comprendí la comunicación entre servicios usando Feign Client.  
3. Entendí el funcionamiento de Eureka Server para registro y descubrimiento.  
4. Pude integrar un API Gateway como punto de entrada unificado.  
5. Realicé pruebas funcionales con Postman para validar los servicios.
