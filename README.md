# App CRUD con SQLite en Android

Esta aplicación Android, desarrollada en Android Studio, implementa un sistema CRUD básico utilizando SQLite. Su objetivo es servir como ejemplo educativo para trabajar con bases de datos locales en Android.

## Descripción de la Aplicación

La app permite realizar operaciones de CRUD (Crear, Leer, Actualizar y Eliminar) en dos tablas relacionadas:
- **Autores**: tabla que almacena la información de los autores.
- **Libros**: tabla que almacena los datos de los libros y está vinculada con los autores.

Además, cuenta con un **demo de login** sencillo.

## Vista Previa

A continuación se muestra un gif con el funcionamiento de la aplicación:

![Funcionamiento de la app](demo.gif)

## Características

- **SQLite**: Uso de SQLite para gestionar la base de datos local en Android.
- **Relaciones entre tablas**: Implementación de una relación entre la tabla de Autores y la de Libros.
- **CRUD Completo**: Crear, leer, actualizar y eliminar registros en ambas tablas.
- **Demo de Login**: Ejemplo simple de inicio de sesión para acceder a las funcionalidades de la app.

## Instalación

1. Clona el repositorio:
    ```bash
    git clone https://github.com/kencrisg/AppCrudAndroid.git
    ```
2. Abre el proyecto en Android Studio.
3. Ejecuta la aplicación en un emulador o dispositivo físico.

## Uso

1. **Login**: Inicia sesión con el demo de login.
2. **Gestión de Autores y Libros**: 
   - Navega por la app para agregar, ver, editar y eliminar autores y libros.
   - Visualiza los libros vinculados a cada autor.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT.

## Autor
Cristian Castro Gómez (kencrisg)
