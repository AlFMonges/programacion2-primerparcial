package com.examenparcial1.universidadapp.data.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "profesores")
public class Profesor {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String nombres;
    public String apellidos;
    public String telefono;
    public String email;

    // constructor
    public Profesor(String nombres, String apellidos, String telefono, String email) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
    }
}
