# Universidad App

## Descripción del Sistema

**Universidad App** es una aplicación de Android diseñada para gestionar las entidades clave dentro de un entorno universitario simple. Permite a los usuarios administrar estudiantes, profesores e inscripciones a través de una interfaz de usuario limpia e intuitiva, construida con las últimas prácticas de desarrollo de Android.

El proyecto utiliza una arquitectura moderna con componentes de Android Jetpack, incluyendo Room para la persistencia de datos, ViewModel para la lógica de la UI, LiveData para la observación de datos y Navigation Component para gestionar el flujo de la aplicación.

---

## Funcionalidades Implementadas

### 1. Gestión de Estudiantes
- **Listado Completo**: Muestra una lista de todos los estudiantes registrados, ordenados alfabéticamente.
- **CRUD Completo**:
    - **Crear**: Añade nuevos estudiantes a través de un formulario dedicado.
    - **Modificar**: Edita la información de un estudiante existente.
    - **Eliminar**: Elimina estudiantes directamente desde la lista, protegido por un diálogo de confirmación para evitar borrados accidentales.

### 2. Gestión de Profesores
- **Listado Completo**: Visualiza a todos los profesores registrados.
- **Operaciones**:
    - **Crear**: Registra nuevos profesores mediante un formulario.
    - **Modificar**: Actualiza los datos de un profesor.
      
### 3. Gestión de Cursos
- **Listado Inteligente**: Muestra todos los cursos disponibles, pero en lugar de mostrar un ID de profesor, realiza una consulta `JOIN` para mostrar directamente el **nombre completo del profesor asignado**, mejorando la legibilidad.

### 4. Gestión de Inscripciones
Esta es la funcionalidad más avanzada del sistema.
- **Listado Detallado**: Presenta una lista de todas las inscripciones. Gracias a una consulta `JOIN` compleja, cada ítem muestra directamente el **nombre completo del estudiante** y el **nombre del curso**, en lugar de solo sus IDs.
- **Formulario de Creación Avanzado**:
    - **Búsqueda de Estudiante por Documento**: Para inscribir a un estudiante, el usuario introduce su número de documento. Al cambiar el foco, la aplicación busca al estudiante y autocompleta su nombre, validando su existencia.
    - **Selección de Curso Inteligente**: En lugar de un `Spinner`, el usuario toca un campo que lo lleva a la lista de cursos. Al seleccionar uno, la aplicación regresa automáticamente al formulario con el curso elegido.
    - **Prevención de Duplicados**: El sistema valida que un estudiante no pueda ser inscrito dos veces en el mismo curso, mostrando un mensaje de error.
- **Eliminación Segura**: Permite eliminar inscripciones desde la lista, mostrando un diálogo de confirmación claro y descriptivo.

