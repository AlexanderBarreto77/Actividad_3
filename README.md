# Proyecto Final - Arquitectura de Microservicios para la Gestión Integral de Publicaciones

## Universidad de las Fuerzas Armadas ESPE
**Departamento de Ciencias de la Computación**  
**Ingeniería en Tecnologías de la Información**  
**Docente:** Geovanny Cudco  
**Asignatura:** Arquitectura de Software  
**Proyecto Final:** Arquitectura de Microservicios para la Gestión Integral de Publicaciones  
**Fecha de Entrega:** 8 de agosto de 2025  

---

##  Título de la Actividad
**Caso de estudio:** Arquitectura de Microservicios para la Gestión Integral de Publicaciones.

---

##  Contexto y Objetivo
Una institución requiere una plataforma distribuida para la gestión completa del ciclo de vida de publicaciones académicas y editoriales (artículos y libros) elaboradas por autores registrados.

El sistema deberá permitir:
- Registro y autenticación segura de usuarios.
- Creación y edición de publicaciones.
- Flujo de revisión colaborativa.
- Control de cambios.
- Aprobación editorial.
- Publicación final en un Catálogo accesible externamente.

Se implementará bajo el paradigma de **microservicios desacoplados** con:
- **Eureka** (descubrimiento dinámico de servicios).
- **API Gateway** (exposición unificada).
- Comunicación síncrona (**REST/Feign**) y asíncrona (**RabbitMQ**).
- **CockroachDB** distribuida para alta disponibilidad y tolerancia a fallos.
- **Servicio de Notificaciones** para alertas clave.

---

##  Alcance Funcional
1. Autenticación/autorización OAuth2 + JWT.
2. Administración de autores y publicaciones.
3. Proceso de revisión editorial multi-etapa.
4. Eventos de publicación hacia el microservicio Catálogo.
5. Gestión de versiones y estados de publicación.
6. Notificaciones multicanal (email, WebSocket, colas).
7. Trazabilidad y auditoría.
8. Monitoreo y observabilidad integral.

---

##  Actores y Roles
- **Autor**: crea y actualiza borradores.
- **Revisor**: evalúa y comenta.
- **Editor/Administrador Editorial**: aprueba publicaciones.
- **Lector/Consumidor**: consulta el catálogo.
- **Sistema de Notificaciones**: envía alertas.
- **Servicio de Catálogo**: expone publicaciones aprobadas.

Roles mínimos: `ROLE_AUTOR`, `ROLE_REVISOR`, `ROLE_EDITOR`, `ROLE_ADMIN`, `ROLE_LECTOR`.

---

##  Microservicios Principales
### 1. **Auth Service**
- Registro de usuarios y roles.
- Emisión y validación de JWT (OAuth2).
- Publicación de eventos a RabbitMQ.

### 2. **Publicaciones Service**
- Gestión de autores y publicaciones.
- Control de versiones y estados.
- Publicación de eventos para Catálogo.

### 3. **Catálogo Service**
- Suscripción a eventos aprobados.
- Indexación de metadatos.
- API pública de consulta.

### 4. **Notificaciones Service**
- Consumo de eventos.
- Notificaciones por Email y WebSockets.

### 5. **Gateway Service**
- Enrutamiento, validación JWT, CORS.
- Circuit breakers (Resilience4j).

### 6. **Eureka Service**
- Descubrimiento dinámico.

### 7. **Observability/Monitoring**
- Métricas (Prometheus), trazas (Jaeger), logs (ELK).

---

##  Modelo de Dominio
**Entidad Publicación (abstracta)**:
- `id`, `titulo`, `resumen`, `palabrasClave`, `estado`, `versionActual`, `fechaCreacion`, `fechaActualizacion`, `autorPrincipalId`, `tipo`, `metadatos`.

**Clases Derivadas**:
- **Artículo**: revistaObjetivo, sección, referencias.
- **Libro**: ISBN, páginas, capítulos.

**Otros**:
- **Autor**: datos personales, roles.
- **Revisión**: estado, comentarios, historial.

---

##  Comunicación y Mensajería (RabbitMQ)
- Exchange: `publication.events` (topic).
- Routing keys: `publication.submitted`, `publication.published`, `user.registered`, etc.
- Queues: `catalog.publications`, `notifications.activity`.

---

##  Base de Datos Distribuida (CockroachDB)
- Multi-nodo (≥3 nodos).
- Esquema por microservicio.
- UUID como PK.
- Outbox Pattern para consistencia.

---

##  Flujo Principal (BPMN narrativo)
1. Autor crea borrador.
2. Envía a revisión.
3. Editor asigna revisores.
4. Revisor emite recomendación.
5. Ciclo de cambios si es necesario.
6. Aprobación final.
7. Publicación en Catálogo.
8. Notificación a usuarios.

---

##  Escenarios de Uso Clave
1. Autor crea libro.
2. Revisor solicita cambios.
3. Editor aprueba.
4. Lector busca publicaciones.
5. Usuario recibe notificación.
6. Administrador consulta historial.

---

##  Seguridad y Autorización
- Spring Security OAuth2 + JWT.
- Roles y permisos granulares.
- Contraseñas cifradas con BCrypt/Argon2id.
- Rate limiting en API Gateway.

---

##  Diagrama de Arquitectura

<img width="960" height="252" alt="arquitectura_microservicios" src="https://github.com/user-attachments/assets/da3f56e6-d6e9-4970-8101-09bc80a1dbac" />

---

##  Tecnologías Principales
- **Backend**: Spring Boot, Spring Cloud, Feign, Resilience4j.
- **Mensajería**: RabbitMQ.
- **Base de Datos**: CockroachDB.
- **Seguridad**: OAuth2, JWT.
- **Monitoreo**: Prometheus, Jaeger, ELK.
- **Despliegue**: Docker, Kubernetes.

---

