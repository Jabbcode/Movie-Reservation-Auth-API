# 🎬 Movie Reservation Auth API

API REST desarrollada con **Spring Boot**, encargada del **registro, autenticación y autorización de usuarios** dentro de un sistema de **reservas de películas**.  
Este servicio gestiona usuarios, roles y tokens JWT, permitiendo proteger los endpoints del sistema principal de reservas.

---

## 📑 Índice

1. [Descripción general](#-descripción-general)
2. [Características principales](#-características-principales)
3. [Tecnologías utilizadas](#-tecnologías-utilizadas)
4. [Estructura del proyecto](#-estructura-del-proyecto)
5. [Instalación y configuración](#-instalación-y-configuración)
6. [Ejecución del proyecto](#-ejecución-del-proyecto)
7. [Endpoints principales](#-endpoints-principales)
8. [Autenticación JWT](#-autenticación-jwt)
9. [Documentación Swagger](#-documentación-swagger)
10. [Docker (opcional)](#-docker-opcional)
11. [Pruebas](#-pruebas)
12. [Contribuir](#-contribuir)
13. [Roadmap](#-roadmap)
14. [Licencia](#-licencia)

---

## 🎥 Descripción general

**Movie Reservation Auth API** es el servicio encargado de la **gestión de usuarios y seguridad** en un ecosistema de microservicios para reservas de películas.  
Su objetivo principal es manejar:
- Registro y autenticación de usuarios.
- Generación y validación de **tokens JWT**.
- Control de acceso basado en **roles** (por ejemplo, ADMIN y USER).

Se puede integrar fácilmente con otros microservicios como:
- `Movie Service` → gestión de películas y horarios.
- `Reservation Service` → gestión de reservas y pagos.

---

## ⚙️ Características principales

- Registro de nuevos usuarios (signup).
- Inicio de sesión y generación de token JWT.
- Validación automática de tokens en endpoints protegidos.
- Roles y permisos personalizables.
- Manejo de excepciones global.
- Integración lista para conectar con otros servicios REST.
- Compatible con **MySQL** y preparado para contenerización con **Docker**.

---

## 🧰 Tecnologías utilizadas

| Tipo | Herramienta |
|------|--------------|
| Lenguaje | Java 17+ |
| Framework principal | Spring Boot |
| Seguridad | Spring Security + JWT |
| Base de datos | MySQL |
| ORM | Spring Data JPA / Hibernate |
| Construcción | Maven |
| Documentación | Springdoc OpenAPI (Swagger UI) |
| Tests | JUnit / Mockito |
| Contenedores | Docker / Docker Compose (opcional) |

---

## 🧱 Estructura del proyecto

```
src/
 ├── main/
 │   ├── java/com/jabbcode/moviereservationauthapi/
 │   │    ├── controller/       # Controladores REST
 │   │    ├── service/          # Lógica de negocio
 │   │    ├── repository/       # Acceso a datos (JPA)
 │   │    ├── model/            # Entidades y DTOs
 │   │    ├── security/         # Configuración de JWT y filtros
 │   │    └── MovieReservationAuthApiApplication.java
 │   └── resources/
 │        ├── application.properties
 │        └── data.sql / schema.sql (opcional)
 └── test/                      # Pruebas unitarias y de integración
```

---

## ⚙️ Instalación y configuración

### 1️⃣ Requisitos previos
- Java 17 o superior  
- Maven 3.6+  
- MySQL en ejecución  
- IDE recomendado: IntelliJ IDEA o VS Code  

### 2️⃣ Clonar el repositorio
```bash
git clone https://github.com/Jabbcode/Movie-Reservation-Auth-API.git
cd Movie-Reservation-Auth-API
```

### 3️⃣ Configurar la base de datos
Edita el archivo `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/movie_auth_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update

# JWT
jwt.secret=miClaveSuperSecreta
jwt.expiration=3600000
```

---

## 🚀 Ejecución del proyecto

Para compilar y ejecutar el proyecto localmente:

```bash
mvn clean install
mvn spring-boot:run
```

La API quedará disponible en:
```
http://localhost:8080
```

---

## 📡 Endpoints principales

| Método | Endpoint | Descripción | Autenticación |
|--------|-----------|--------------|----------------|
| `POST` | `/api/auth/signup` | Registro de nuevo usuario | ❌ |
| `POST` | `/api/auth/login` | Login y generación de JWT | ❌ |
| `GET` | `/api/users/me` | Datos del usuario autenticado | ✅ |
| `GET` | `/api/admin/users` | Listado de todos los usuarios | ✅ (solo ADMIN) |

---

## 🔐 Autenticación JWT

El proceso de autenticación se basa en **JSON Web Tokens (JWT)**:
1. El usuario envía sus credenciales a `/api/auth/login`.
2. El servidor valida y genera un token firmado.
3. El cliente usa el token en cada solicitud protegida:

```http
Authorization: Bearer <tu_token_jwt>
```

Los filtros de Spring Security validan el token y cargan los datos del usuario automáticamente.

---

## 📘 Documentación Swagger

Una vez ejecutado el proyecto, accede a la documentación interactiva:

```
http://localhost:8080/swagger-ui/index.html
```

Allí podrás probar los endpoints directamente desde el navegador.

---

## 🐳 Docker (opcional)

Puedes contenerizar la API con un `Dockerfile` similar a este:

```dockerfile
FROM openjdk:17-jdk-slim
COPY target/movie-reservation-auth-api.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
```

Y ejecutarlo con:

```bash
docker build -t movie-auth-api .
docker run -p 8080:8080 movie-auth-api
```

---

## 🧪 Pruebas

Ejecuta las pruebas unitarias y de integración con:

```bash
mvn test
```

Se recomienda configurar un perfil de test con base de datos en memoria (`H2`) para ejecución rápida.

---

## 🤝 Contribuir

Las contribuciones son bienvenidas ❤️  
Para colaborar:

1. Haz un **fork** del repositorio.  
2. Crea una nueva rama (`feature/nueva-funcionalidad`).  
3. Realiza tus cambios y haz commit.  
4. Envía un **Pull Request** con una breve descripción.  

Por favor, asegúrate de que los tests pasen antes de enviar el PR.

---

## 🗺️ Roadmap

- [ ] Implementar recuperación de contraseñas  
- [ ] Integrar 2FA (autenticación en dos pasos)  
- [ ] Añadir refresco automático de tokens  
- [ ] Docker Compose con MySQL y backend  
- [ ] Integrar módulo de reservas  

---

## 🪪 Licencia

Este proyecto está bajo la **Licencia MIT**.  
Puedes usarlo, modificarlo y distribuirlo libremente, siempre que se mantenga la atribución al autor original.

---

> Desarrollado con ❤️ por [Jabbcode](https://github.com/Jabbcode)
