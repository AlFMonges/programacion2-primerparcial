package com.examenparcial1.universidadapp.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.examenparcial1.universidadapp.data.entities.Curso;
import com.examenparcial1.universidadapp.data.entities.Inscripcion;

import java.util.List;

public class CursoConInscripciones {
    @Embedded
    public Curso curso;

    @Relation(
            parentColumn = "id",
            entityColumn = "cursoId"
    )
    public List<Inscripcion> inscripciones;
}
