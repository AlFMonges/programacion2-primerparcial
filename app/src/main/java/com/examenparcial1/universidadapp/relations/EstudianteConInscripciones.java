package com.examenparcial1.universidadapp.relations;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.examenparcial1.universidadapp.data.entities.Estudiante;
import com.examenparcial1.universidadapp.data.entities.Inscripcion;

import java.util.List;

public class EstudianteConInscripciones {
    @Embedded
    public Estudiante estudiante;

    @Relation(
            parentColumn = "id",
            entityColumn = "estudianteId"
    )
    public List<Inscripcion> inscripciones;
}
