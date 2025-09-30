package com.examenparcial1.universidadapp.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.examenparcial1.universidadapp.data.entities.Inscripcion;

import java.util.List;
@Dao
public interface InscripcionDao {

    @Query("SELECT * FROM inscripciones ORDER BY fechaInscripcion DESC")
    LiveData<List<Inscripcion>> obtenerInscripciones();

    @Insert
    void insertar(Inscripcion inscripcion);

    @Update
    void actualizar(Inscripcion inscripcion);

    @Delete
    void eliminar(Inscripcion inscripcion);
}