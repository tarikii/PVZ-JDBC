# Proyecto JDBC PostgreSQL

Este proyecto utiliza JDBC para conectarse a una base de datos PostgreSQL y realizar operaciones CRUD (crear, leer, actualizar y eliminar).


# Requisitos previos
- Java 8 o posterior
- Driver JDBC de PostgreSQL
- Base de datos PostgreSQL en ejecución y funcional

# Configuración
Antes de ejecutar el proyecto, es necesario configurar la conexión a la base de datos PostgreSQL. 
Para ello, se deben modificar los siguientes valores en el archivo src/main/resources/db.properties:

- host        Aqui se le asigna la IP del vagrant en el que estamos usando el proyecto
- port        5432
- dbname      pvz
- user        tarik
- password    lechuza

# Uso
Una vez que el proyecto esté en ejecución y funcione todo, puedes realizar peticiones a la base de datos "pvz", creando, borrando, modificando tablas y rellenandolas
con el CSV que se ha sacado de la anterior practica de WebScrapping.
