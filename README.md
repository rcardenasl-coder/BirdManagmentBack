\# BirdRegistryAPI

Simple API para gestionar registros de aves: crear, actualizar, recuperar y eliminar datos de aves.

\## Descripción

Este proyecto es una API RESTful construida con \*\*Spring Boot\*\* que utiliza una base de datos en memoria H2 para gestionar registros de aves. Está diseñada siguiendo los principios de la \*\*arquitectura hexagonal\*\* (también conocida como Ports and Adapters), lo que permite una separación clara entre la lógica de negocio, los adaptadores de entrada/salida y las dependencias externas.

\## Requisitos previos

\- \*\*Java 21\*\* instalado.

\- \*\*Maven\*\* instalado (para compilar y ejecutar el proyecto).

\- \*\*Docker\*\* y \*\*Docker Compose\*\* (opcional, para ejecutar SonarQube).

\---

\## Configuración inicial

\### Propiedades de la aplicación

El proyecto utiliza las siguientes configuraciones en el archivo \`application.properties\`:

\`\`\`properties

spring.application.name=BirdRegistryAPI

\# Configuración de la consola H2

spring.h2.console.enabled=true

spring.h2.console.path=/h2-console

\# Configuración de la base de datos H2 en memoria

spring.datasource.url=jdbc:h2:mem:dbbird

spring.datasource.driver-class-name=org.h2.Driver

spring.datasource.username=ejemplo

spring.datasource.password=

\# Configuración de JPA/Hibernate

spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true

\`\`\`

Esto habilita:

\- Una base de datos H2 en memoria accesible en \`jdbc:h2:mem:dbbird\`.

\- La consola web de H2 en \`http://localhost:8080/h2-console\`.

\- La generación automática de esquemas (\`ddl-auto=update\`) y la visualización de consultas SQL en la consola.

\---

\## Cómo ejecutar el proyecto

\### 1. Clonar el repositorio

\`\`\`bash

git clone [https://github.com/rcardenasl-coder/BirdManagmentBack.git](https://github.com/rcardenasl-coder/BirdManagmentBack.git)

cd bird-registry-api

\`\`\`

\### 2. Compilar el proyecto con Maven

\`\`\`bash

mvn clean install

\`\`\`

\### 3. Ejecutar la aplicación

Hay dos formas de ejecutar el proyecto:

\#### Opción 1: Usando Maven

\`\`\`bash

mvn spring-boot:run

\`\`\`

\#### Opción 2: Usando el archivo JAR generado

Después de compilar, genera el JAR y ejecútalo:

\`\`\`bash

mvn package

java -jar target/bird-registry-api-0.0.1-SNAPSHOT.jar

\`\`\`

\### 4. Acceder a la aplicación

\- La API estará disponible en: \`http://localhost:8080\`.

\- La consola H2 estará disponible en: \`http://localhost:8080/h2-console\`.

\- Usa la URL \`jdbc:h2:mem:dbbird\`, usuario \`ejemplo\` y deja la contraseña en blanco.

\---

\## Integración con SonarQube (Opcional)

El proyecto incluye un archivo \`docker-compose.yml\` para ejecutar SonarQube localmente y analizar la calidad del código.

\### 1. Iniciar SonarQube con Docker Compose

\`\`\`bash

docker-compose up -d

\`\`\`

\- SonarQube estará disponible en \`http://localhost:9000\`.

\- Usa las credenciales predeterminadas: usuario \`admin\`, contraseña \`admin\` (cámbiala tras el primer inicio).

\### 2. Ejecutar el análisis de SonarQube

Asegúrate de que SonarQube esté corriendo y ejecuta:

\`\`\`bash

mvn sonar:sonar

\`\`\`

\- El análisis usará las propiedades definidas en el \`pom.xml\`:

\- \`sonar.host.url=http://localhost:9000\`

\- \`sonar.login=squ\_5b43b0d4687d27b95cfa43caacb02da850774b66\`

Los resultados estarán disponibles en la interfaz de SonarQube.

\---

\## Arquitectura Hexagonal

Este proyecto sigue los principios de la \*\*arquitectura hexagonal\*\*, lo que significa que:

\- La \*\*lógica de negocio\*\* (dominios y casos de uso) está en el núcleo y es independiente de frameworks o tecnologías externas.

\- Los \*\*puertos\*\* definen las interfaces de entrada (controladores REST) y salida (repositorios).

\- Los \*\*adaptadores\*\* conectan el núcleo con el mundo exterior:

\- Adaptadores de entrada: Controladores REST para recibir solicitudes HTTP.

\- Adaptadores de salida: Implementaciones de JPA con H2 para persistencia.

Esto permite:

\- Mayor facilidad para cambiar la base de datos (por ejemplo, de H2 a MySQL) sin modificar la lógica de negocio.

\- Pruebas unitarias más simples al inyectar mocks en los puertos.

\- Una estructura modular y mantenible.

\---

\## Dependencias principales

\- \*\*Spring Boot 3.4.4\*\*: Framework base.

\- \*\*H2 Database\*\*: Base de datos en memoria para desarrollo.

\- \*\*Spring Data JPA\*\*: Persistencia de datos.

\- \*\*Springdoc OpenAPI\*\*: Documentación automática de la API en \`http://localhost:8080/swagger-ui.html\`.

\- \*\*Lombok\*\*: Reducción de código boilerplate.

\- \*\*JaCoCo\*\*: Cobertura de pruebas (mínimo 80% configurado).

\---

\## Comandos útiles de Maven

\- Generar informe de cobertura: \`mvn verify\`

\- Limpiar y construir: \`mvn clean package\`

\- Ejecutar pruebas: \`mvn test\`

\---
