# API de Usuarios y Posts

Esta API proporciona una interfaz para crear, modificar, eliminar y listar usuarios y sus posts.

## Reglas de Negocio

- Solo los usuarios autenticados pueden crear, modificar y eliminar sus propios posts.
- Los usuarios pueden listar todos los usuarios y sus posts correspondientes.
- No se permite a un usuario modificar o eliminar posts que pertenezcan a otro usuario.
- Todos los endpoints relacionados con las operaciones de posts están protegidos y requieren autenticación.

## Requisitos

Para ejecutar este proyecto se necesita tener instalado:

- [Docker](https://www.docker.com/)
- [Postman](https://www.postman.com/) (opcional, para realizar pruebas de la API)

## Instrucciones de Uso

### Levantar la Aplicación con Docker

1. Clone el repositorio a su máquina local.
2. Navegue a la raíz del proyecto donde se encuentra el `Dockerfile`.
3. Construya la imagen de Docker con el siguiente comando:
   ```bash
   docker build -t projectbci .
4. Una vez construida la imagen, ejecute un contenedor en esa imagen:

    ```bash
   docker run -p 8080:8080 projectbci

5. La aplicación ahora estará disponible en `localhost:8080`.

## Endpoints de la API

La API proporciona los siguientes endpoints:

### Usuarios

- `POST /users/` - Crear un nuevo usuario.
- `POST /login/users/` - Autenticacion y generacion del token de acceso.
- `GET /users/` - Listar todos los usuarios.

## Pruebas con Postman

Para probar la API con Postman, puede importar la colección de endpoints provista en el archivo `Reto BCI.postman_collection.json` dentro del proyecto.

## Manejo de Errores

La API devuelve códigos de estado HTTP apropiados junto con mensajes descriptivos en caso de errores.

## Seguridad

La API utiliza autenticación básica para proteger los endpoints.