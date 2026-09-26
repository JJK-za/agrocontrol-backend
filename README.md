# agrocontrol-backend

## Capítulo 03 - Introducción a Spring Boot

### Ficha
- Nombre del sistema: AgroControl
- Nombre del backend: agrocontrol-backend
- Package base: com.agrocontrol
- Entidad padre: Campana
- Entidad dependiente: Labor
- Relación: 1 Campaña tiene muchas Labores

### Evidencia
- GET /api/health → 200 OK, con ProjectInfoService inyectado por constructor (Bean).

## Capítulo 04 - Spring MVC, DTOs y Validación

### Contrato HTTP

| Verbo | Ruta | Entrada | Salida | Status |
|---|---|---|---|---|
| POST | /api/campanas | CrearCampanaRequest (JSON) | CampanaResponse | 201 / 400 |
| GET | /api/campanas | estado (query param opcional) | Lista de CampanaResponse | 200 |
| GET | /api/campanas/{id} | id (path variable) | CampanaResponse | 200 / 404 |

### Decisiones
- Request y Response son DTOs distintos: el Request no incluye id (lo genera el
  sistema); el Response sí.
- @Valid + @NotNull en CrearCampanaRequest protege que parcelaId, cultivoId y
  fechaInicio sean obligatorios; una petición sin alguno de ellos responde 400
  automáticamente, sin que el controller tenga que validarlo a mano.
- GET /api/campanas/{id} responde 404 cuando el id no existe, usando Optional
  del CampanaService.

### Pruebas realizadas
- POST válido → 201
- POST inválido (sin cultivoId) → 400
- GET lista → 200
- GET id existente → 200
- GET id inexistente → 404