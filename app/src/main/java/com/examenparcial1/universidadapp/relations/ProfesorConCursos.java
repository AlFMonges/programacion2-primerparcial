package com.examenparcial1.universidadapp.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.examenparcial1.universidadapp.data.entities.Curso;
import com.examenparcial1.universidadapp.data.entities.Profesor;

import java.util.List;

public class ProfesorConCursos {
    @Embedded
    public Profesor profesor;

    @Relation(parentColumn = "id", entityColumn = "profesorId")
    public List<Curso> cursos;
}
