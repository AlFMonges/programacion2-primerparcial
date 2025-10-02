package com.examenparcial1.universidadapp.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import java.util.Date;

@Entity(tableName = "inscripciones",
        primaryKeys = {"estudianteId", "cursoId"},
        foreignKeys = {
                @ForeignKey(entity = Estudiante.class,
                            parentColumns = "id",
                            childColumns = "estudianteId",
                            onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = Curso.class,
                            parentColumns = "id",
                            childColumns = "cursoId",
                            onDelete = ForeignKey.CASCADE)
        },
        indices = {@Index("estudianteId"), @Index("cursoId")}
)
public class Inscripcion {

    public int estudianteId;
    public int cursoId;
    public Date fechaInscripcion;

    public Inscripcion(int estudianteId, int cursoId, Date fechaInscripcion) {
        this.estudianteId = estudianteId;
        this.cursoId = cursoId;
        this.fechaInscripcion = fechaInscripcion;
    }
}
