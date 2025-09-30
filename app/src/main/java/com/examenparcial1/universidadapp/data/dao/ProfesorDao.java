package com.examenparcial1.universidadapp.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.examenparcial1.universidadapp.data.entities.Profesor;

import java.util.List;

@Dao
public interface ProfesorDao {

    @Query("SELECT * FROM profesores ORDER BY nombres ASC")
    LiveData<List<Profesor>> obtenerProfesores();

    @Insert
    void insertar(Profesor profesor);

    @Update
    void actualizar(Profesor profesor);

    @Delete
    void eliminar(Profesor profesor);
}

