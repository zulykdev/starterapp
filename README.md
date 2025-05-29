# starterapp

Starterapp es una aplicación base para gestionar el registro y autenticación de usuarios a través de una API RESTful. El proyecto está construido con Java y Spring Boot, e implementa buenas prácticas de seguridad, documentación, y arquitectura modular.

---

## Detalle Funcional (¿Qué hace?)

- **Registro de usuarios:** Permite registrar usuarios nuevos enviando datos como nombre, email, contraseña y teléfonos.
- **Validación de datos:** Valida el formato del email y la seguridad de la contraseña.
- **Prevención de duplicados:** Impide el registro de usuarios con un email ya existente.
- **Generación de JWT:** Al crear un usuario, se genera un token JWT que puede utilizarse para autenticación en futuras solicitudes.
- **Gestión de teléfonos:** Permite asociar múltiples teléfonos a un usuario y guarda la relación en la base de datos.
- **Auditoría y seguimiento:** Almacena información sobre el último inicio de sesión y estado de activación del usuario.
- **API Documentada:** Incluye configuración Swagger/OpenAPI para explorar y probar los endpoints de la API.
- **Pruebas unitarias:** Contiene pruebas unitarias para los servicios principales.

---

## Detalle Técnico (¿Qué módulos utiliza y para qué sirve?)

### Módulos principales:

- **Spring Boot:** Marco principal para el desarrollo de la aplicación, facilita la configuración y despliegue rápido.
- **Spring Data JPA:** Gestiona el acceso a la base de datos y el mapeo objeto-relacional (ORM) para las entidades de usuario y teléfono.
- **Spring Security:** Proporciona seguridad a la aplicación, gestionando autenticación y autorización de endpoints.
- **JWT (Json Web Token):** Utilizado para la generación y validación de tokens de sesión de usuario (`JwtUtil.java`).
- **Swagger/OpenAPI:** Expone documentación interactiva de la API para facilitar el desarrollo y pruebas (`SwaggerConfig.java`).
- **MapStruct:** Mapea objetos entre DTOs y entidades de dominio para facilitar la transferencia de datos (`UserMapper.java`).
- **Validaciones personalizadas:** Anotaciones y utilidades para validar emails y contraseñas con reglas propias.
- **Lombok:** Reduce la verbosidad del código mediante la generación automática de getters/setters y constructores.
- **JUnit y Mockito:** Herramientas empleadas para pruebas unitarias de los servicios de la aplicación.

### Estructura relevante de archivos:

- `StarterApplication.java`: Clase principal de arranque.
- `service/UserService.java`: Lógica de negocio principal para el registro y gestión de usuarios.
- `dto/request/UserRequest.java`: Objeto de transferencia de datos para peticiones de registro.
- `dto/response/UserResponse.java`: Objeto de transferencia para las respuestas de usuario.
- `util/mappers/UserMapper.java`: Transformaciones entre entidades y DTOs.
- `config/SecurityConfig.java`: Configuración de seguridad (autorización y autenticación).
- `config/SwaggerConfig.java`: Configuración de la documentación Swagger.
- `security/JwtUtil.java`: Utilidad para la gestión de tokens JWT.

## Ejemplo de uso de endpoint

### Registro de usuario

**POST** `/v1/customers/onboarding`

- **Descripción:** Permite registrar un nuevo usuario en el sistema.
- **Content-Type:** `application/json`
- **Respuesta exitosa:** `201 Created` y cuerpo JSON con los datos del usuario registrado y su token.

**Ejemplo de Request:**
```json
{
    "name": "Juan Rodriguez",
    "email": "hanz@asdfg.org",
    "password": "hunter21",
    "phones": [
        {
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
}
```

**Ejemplo de Response:**
```json
{
    "id": "uuid-generado",
    "created": "2025-05-29T10:51:09Z",
    "modified": "2025-05-29T10:51:09Z",
    "last_login": "2025-05-29T10:51:09Z",
    "token": "jwt-token-generado",
    "isactive": true
}
```

**Errores comunes:**
- Si el email ya existe:
  ```json
  {
    "mensaje": "El correo electrónico ya está registrado"
  }
  ```
- Si el email o contraseña no cumplen las validaciones, se retorna un error de validación estándar.

## Arquitectura de la Solución

```mermaid
graph TD
    subgraph Controller
        UC[UserController]
    end
    subgraph Service
        US[UserService]
    end
    subgraph Repository
        UR[UserRepository]
    end
    subgraph Data
        U[User]
        P[Phone]
    end
    subgraph DTO
        URq[UserRequest]
        UPq[PhoneRequest]
        URs[UserResponse]
        UPs[PhoneResponse]
    end
    subgraph Seguridad
        JWT[JwtUtil]
        SC[SecurityConfig]
    end
    subgraph Configuración
        SW[SwaggerConfig]
    end
    subgraph Utilidades
        UM[UserMapper]
        C[Constants]
    end

    UC -- "Recibe y valida DTO de entrada" --> URq
    UC -- "Devuelve DTO de salida" --> URs
    UC --> US
    US --> UM
    US --> UR
    US --> JWT
    UR --> U
    U -- "Relaciona" --> P
    UM -- "Transforma" --> URq
    UM -- "Transforma" --> U
    UM -- "Transforma" --> URs
    UM -- "Transforma" --> P
    JWT -- "Genera/valida tokens" --> US
    SC -- "Configura seguridad" --> UC
    SW -- "Documenta API" --> UC
    C -- "Mensajes y constantes" --> US
```
---

### Puesta en marca:
Para iniciar la aplicación, asegúrate de tener Java 17 o superior y Maven instalado. Luego, ejecuta el siguiente comando en la raíz del proyecto:

```bash
mvn clean package
mvn clean install
```
Una vez dentro del proyecto ejecutar el archivo: StarterApplication.java

Utiliza H2 (BD) in memory por lo que no es necesario configurar una base de datos externa. La aplicación se ejecutará en el puerto 8081 por defecto.
Importar el postman collection: StarterApp.postman_collection y ejecutar.

---

**Leyenda rápida:**
- **DTO:** Objetos de transferencia de datos (entrada/salida).
- **Controller:** Maneja las peticiones HTTP y respuestas.
- **Service:** Lógica de negocio.
- **Repository:** Acceso a datos persistentes.
- **Data:** Entidades de dominio (modelo).
- **Seguridad:** Gestión de autenticación/autorización y tokens.
- **Configuración:** Documentación y seguridad.
- **Utilidades:** Mappers y constantes compartidas.

---


## Enlaces y Referencias

- [Documentación oficial de Spring Boot](https://spring.io/projects/spring-boot)
- [Swagger/OpenAPI](https://swagger.io/specification/)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Spring Security](https://spring.io/projects/spring-security)

---

> **Nota:** Esta documentación fue generada automáticamente. Para obtener detalles adicionales o revisar todo el código, visita el [repositorio en GitHub](https://github.com/zulykdev/starterapp).
> 