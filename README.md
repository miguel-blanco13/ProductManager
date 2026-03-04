## Participantes

| Usuario                                                | Rol           |
|--------------------------------------------------------|---------------|
| [@miguel-blanco13](https://github.com/miguel-blanco13) | Desarrollador |
| [@Joseph11-h](https://github.com/Joseph11-h)           | Desarrollador |

---
# ProductManager

Sistema de gestión de productos por consola desarrollado en Java, con arquitectura **MVP (Model-View-Presenter)** y estructura de datos de **lista enlazada simple** implementada manualmente.

> Proyecto académico — Universidad Pedagógica y Tecnológica de Colombia (UPTC)

---

## Funcionalidades

- **Agregar producto** — Registra un producto con descripción, precio y unidad de medida.
- **Listar productos** — Muestra todos los productos registrados en orden de ingreso.
- **Listar ordenados por nombre** — Muestra los productos ordenados alfabéticamente por descripción.
- **Eliminar productos** — Elimina todos los productos que coincidan exactamente con el nombre ingresado.
- **Interfaz visual en consola** — Menú con bordes, colores ANSI y formato tabular.

---

## Arquitectura

El proyecto sigue el patrón **MVP (Model-View-Presenter)**, separando responsabilidades en tres capas bien definidas:

```
Main
 └── Runner              <- Ensambla y conecta las tres capas
      ├── Model          <- Lógica de negocio y estructura de datos
      ├── View           <- Interfaz de usuario por consola
      └── Presenter      <- Controlador: coordina Model y View
```

### Estructura de paquetes

```
src/co/edu/uptc/
├── Main.java
├── interfaces/
│   ├── ModelInterface.java
│   ├── ViewInterface.java
│   └── PresenterInterface.java
├── model/
│   └── ProductModelManagerList.java   <- Lista enlazada simple
├── pojo/
│   ├── Product.java                   <- Entidad: descripción, precio, unidad
│   └── ProductNode.java               <- Nodo de la lista enlazada
├── presenter/
│   ├── ProductPresenter.java
│   └── Runner.java
└── view/
    └── ConsoleView.java               <- UI con colores ANSI
```

---

## Estructura de datos

La persistencia en memoria se maneja con una **lista enlazada simple** implementada desde cero (`ProductNode`), sin usar colecciones de Java para el almacenamiento principal. Cada nodo contiene un `Product` y una referencia al siguiente nodo.

### Atributos de un Producto

| Campo           | Tipo     | Descripcion              |
|-----------------|----------|--------------------------|
| `description`   | `String` | Nombre del producto      |
| `price`         | `double` | Precio unitario          |
| `unitOfMeasure` | `String` | Unidad (kg, lt, un, etc.)|

---

## Como ejecutar

### Requisitos

- Java JDK 11 o superior
- Terminal compatible con colores ANSI (Linux/macOS nativo; Windows: usa Git Bash o Windows Terminal)

### En Linux / macOS

```bash
chmod +x run.sh
./run.sh
```

### En Windows

```cmd
run.bat
```

### Manual

```bash
# Compilar
javac -d out/production/ProductManager @sources.txt

# Ejecutar
java -cp out/production/ProductManager co.edu.uptc.Main
```

---

## Vista previa

```
+-------------------------------------------------------+
|          ADMINISTRADOR DE PRODUCTOS - UPTC            |
|-------------------------------------------------------|
|                                                       |
|  1.  Adicionar producto                               |
|  2.  Listar productos                                 |
|  3.  Mostrar productos ordenados por nombre           |
|  4.  Borrar productos                                 |
|  5.  Salir                                            |
|                                                       |
|-------------------------------------------------------|
```

---


## Institucion

**Universidad Pedagógica y Tecnológica de Colombia — UPTC**  
Escuela de Ingeniería de Sistemas y Computación
