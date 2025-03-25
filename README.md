# BirdRegistryAPI 🐦

**Simple API para gestionar registros de aves: crear, actualizar, recuperar y eliminar datos de aves.**

---

## Descripción

**BirdRegistryAPI** es una API RESTful desarrollada con **Spring Boot** que utiliza una base de datos en memoria **H2** para gestionar información sobre aves. Está diseñada con la **arquitectura hexagonal** (Ports and Adapters), asegurando una clara separación entre la lógica de negocio, los adaptadores de entrada/salida y las dependencias externas.

---

## Requisitos previos

- **Java 21** instalado ☕
- **Maven** para compilar y ejecutar el proyecto 🛠️
- **Docker** y **Docker Compose** (opcional, para SonarQube) 🐳

---

## Configuración inicial

### Propiedades de la aplicación

El archivo `application.properties` contiene:

```properties
spring.application.name=BirdRegistryAPI

# Consola H2
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Base de datos H2 en memoria
spring.datasource.url=jdbc:h2:mem:dbbird
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=ejemplo
spring.datasource.password=

# JPA/Hibernate
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
Qué hace:

Base de datos H2 en jdbc:h2:mem:dbbird.
Consola H2 en http://localhost:8080/h2-console.
Generación automática de esquemas y logs SQL.
## 🚀 Cómo ejecutar el proyecto
1. Clonar el repositorio git clone https://github.com/rcardenasl-coder/BirdManagmentBack.git
2. ``cd bird-registry-api``
3. Compilar con Maven: 
```mvn clean install```
4. Ejecutar la aplicación:
- #### Opción 1: Con Maven ``mvn spring-boot:run``
- #### Opción 2: Con JAR ``mvn package java -jar target/bird-registry-api-0.0.1-SNAPSHOT.jar``
5. Acceder:
- API: http://localhost:8080
- - Consola H2: http://localhost:8080/h2-console
- - URL: jdbc:h2:mem:dbbird
- - Usuario: ejemplo
- - Contraseña: (en blanco)
## 🔍 Integración con SonarQube (Opcional)
Analiza la calidad del código con SonarQube usando Docker.

1. Iniciar SonarQube

- ``docker-compose up -d``
- URL: http://localhost:9000
- Credenciales: admin / admin (cámbialas al iniciar).
2. Ejecutar análisis
- ``mvn sonar:sonar``
- Configuración en pom.xml:
- - sonar.host.url=http://localhost:9000
- - sonar.login=squ_5b43b0d4687d27b95cfa43caacb02da850774b66(Dependiendo del token del SonarQube)
## 🏛️ Arquitectura Hexagonal
**El proyecto sigue la arquitectura hexagonal:**

**Núcleo:** Lógica de negocio independiente.  
**Puertos:** Interfaces de entrada (REST) y salida (repositorios).  
**Adaptadores:**
- Entrada: Controladores REST.
- Salida: Persistencia con JPA/H2.  

**Ventajas:**
- Cambio fácil de tecnologías externas.
- Pruebas unitarias simplificadas.
- Código modular y limpio.
## 📦 Dependencias principales
- **Spring Boot 3.4.4:** Base del proyecto.
- **H2 Database:** Base de datos en memoria.
- **Spring Data JPA:** Persistencia.
- **Springdoc OpenAPI:** Docs en http://localhost:8080/swagger-ui.html.
- **Lombok:** Menos boilerplate.
- **JaCoCo:** Cobertura mínima del 80%.