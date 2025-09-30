package com.examenparcial1.universidadapp.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "cursos",
        foreignKeys = {
                @ForeignKey(entity = Profesor.class,
                            parentColumns = "id",
                            childColumns = "profesorId",
                            onDelete = ForeignKey.SET_NULL),
        },
        indices = {@Index("profesorId")}
)


public class Curso {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String codigo;
    public String nombre;
    public int creditos;
    @ColumnInfo(name = "profesorId")
    public int profesorId;

    // constructor
    public Curso(String codigo, String nombre, int creditos, int profesorId) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
        this.profesorId = profesorId;
    }
}
