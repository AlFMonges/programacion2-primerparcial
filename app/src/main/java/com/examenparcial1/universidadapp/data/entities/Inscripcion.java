package com.examenparcial1.universidadapp.data.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
// Ya no es necesaria la importación de @PrimaryKey aquí si solo se usa en @Entity

import java.util.Date;

@Entity(tableName = "inscripciones",
        primaryKeys = {"estudianteId", "cursoId"}, // Esta es la clave primaria correcta
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

     public Inscripcion( int cursoId, Date fechaInscripcion) {
         this.cursoId = cursoId;
         this.fechaInscripcion = fechaInscripcion;
     }
}
