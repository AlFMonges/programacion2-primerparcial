package com.examenparcial1.universidadapp.relations;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import com.examenparcial1.universidadapp.data.entities.Curso;
import com.examenparcial1.universidadapp.data.entities.Estudiante;
import com.examenparcial1.universidadapp.data.entities.Inscripcion;

import java.util.List;

public class EstudianteConCursos {
    @Embedded
    public Estudiante estudiante;
    @Relation(parentColumn = "id", entityColumn = "id", associateBy = @Junction(value = Inscripcion.class,
            parentColumn = "estudianteId", entityColumn = "cursoId"))
    public List<Curso> cursos;
}
