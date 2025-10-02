package com.examenparcial1.universidadapp.data.relations;

import androidx.room.Embedded;
import com.examenparcial1.universidadapp.data.entities.Curso;

// Esta clase es un POJO (Plain Old Java Object) que Room usará para
// mapear los resultados de la consulta con JOIN.
public class CursoConProfesor {

    // Room poblará el objeto Curso con todas las columnas de la tabla de cursos.
    @Embedded
    public Curso curso;

    // Room asignará el resultado de la columna calculada "nombreProfesor" a este campo.
    public String nombreProfesor;
}
