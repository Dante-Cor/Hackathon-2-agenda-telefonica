# 📞 Sistema de Gestión de Agenda Telefónica

Este proyecto implementa una Agenda Telefónica básica en Java, siguiendo principios de **Programación Orientada a Objetos (POO)** y **Buenas Prácticas de Diseño**, como la Separación de Responsabilidades y el manejo de Excepciones Personalizadas.

---

## 🛠️ Estructura del Proyecto (Diseño Modular)

El código está organizado en cuatro paquetes distintos para asegurar un diseño limpio y modular:

| Paquete | Rol | Clases/Interfaces Clave |
| :--- | :--- | :--- |
| **`model`** | **Datos/Entidad.** Define la estructura de los objetos. | `Contacto` |
| **`exceptions`** | **Manejo de Errores.** Define excepciones de negocio. | `ContactoException`, `AgendaLlenaException` |
| **`service`** | **Lógica de Negocio.** Contiene las reglas del sistema. | `InterfaceAgenda` (Interfaz), `Agenda` (Implementación) |
| **`main`** | **Presentación.** Punto de entrada y menú de usuario. | `Main` |

### Principios de POO Destacados

* **Programación a una Interfaz:** La capa de presentación (`main`) interactúa exclusivamente a través de la interfaz **`InterfaceAgenda`**, desacoplando la lógica de negocio.
* **Ordenamiento Natural:** La clase `Contacto` implementa la interfaz **`Comparable`** y el método **`compareTo()`** para definir la ordenación alfabética por nombre y apellido.
* **Integridad de Datos:** La detección de duplicados se basa en la sobrescritura de **`equals()`** y **`hashCode()`** (comparando solo nombre y apellido, sin distinción de mayúsculas).

---

## 🚀 Funcionalidades Principales

1.  **Añadir Contacto:** Agrega un contacto, validando datos y duplicados.
2.  **Listar Contactos:** Muestra todos los contactos **ordenados alfabéticamente**.
3.  **Buscar Contacto:** Permite buscar por nombre y apellido y muestra el teléfono.
4.  **Eliminar Contacto:** Elimina un contacto existente.
5.  **Modificar Teléfono:** Actualiza el número de teléfono de un contacto.
6.  **Validación de Espacio:** Verifica si la agenda está llena y cuántos espacios quedan.

---

## 🚨 Manejo de Errores

El proyecto utiliza **Excepciones Personalizadas** (`RuntimeException`) para gestionar las fallas de negocio de manera controlada:

| Excepción | Propósito |
| :--- | :--- |
| `AgendaLlenaException` | Se lanza al intentar añadir un contacto cuando se ha alcanzado el límite máximo de la agenda. |
| `ContactoException` | Se lanza para errores relacionados con los datos o la existencia del contacto (duplicado, nombre vacío, no encontrado para eliminación/modificación). |

---

## ▶️ Requisitos y Ejecución

### Requisitos

* Java Development Kit (JDK) 8 o superior.

### Cómo Ejecutar

1.  Compila las clases del proyecto, manteniendo la estructura de paquetes.
2.  Ejecuta la clase principal ubicada en el paquete `main`:

```bash
java main.Main