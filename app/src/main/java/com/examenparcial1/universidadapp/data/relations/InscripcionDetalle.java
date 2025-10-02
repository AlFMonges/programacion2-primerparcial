package com.examenparcial1.universidadapp.data.relations;

import androidx.room.Embedded;
import com.examenparcial1.universidadapp.data.entities.Inscripcion;

/**
 * POJO para encapsular el resultado de un JOIN entre Inscripciones,
 * Estudiantes y Cursos.
 */
public class InscripcionDetalle {

    // Room poblará el objeto Inscripcion con sus datos
    @Embedded
    public Inscripcion inscripcion;

    // Campos adicionales que vienen de las tablas unidas (JOIN)
    public String nombreEstudiante;
    public String nombreCurso;
}
