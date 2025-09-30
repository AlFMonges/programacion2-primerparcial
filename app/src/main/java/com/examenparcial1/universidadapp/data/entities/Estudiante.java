package com.examenparcial1.universidadapp.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "estudiantes")
public class Estudiante {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "nro_documento")
    public String nroDocumento;
    @ColumnInfo(name = "nombres")
    public String nombres;
    @ColumnInfo(name = "apellidos")
    public String apellidos;
    @ColumnInfo(name = "telefono")
    public String telefono;
    @ColumnInfo(name = "email")
    public String email;

    // constructor
    public Estudiante(String nroDocumento, String nombres, String apellidos, String telefono, String email) {
        this.nroDocumento = nroDocumento;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.email = email;
    }
}
