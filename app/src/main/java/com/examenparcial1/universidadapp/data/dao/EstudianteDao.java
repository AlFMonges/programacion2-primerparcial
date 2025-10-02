package com.examenparcial1.universidadapp.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.examenparcial1.universidadapp.data.entities.Estudiante;

import java.util.List;

@Dao
public interface EstudianteDao {
    @Insert
    void insertar(Estudiante estudiante);

    @Update
    void actualizar(Estudiante estudiante);

    @Delete
    void eliminar(Estudiante estudiante);

    @Query("SELECT * FROM estudiantes ORDER BY apellidos, nombres")
    LiveData<List<Estudiante>> obtenerTodos();

    @Query("SELECT * FROM estudiantes WHERE id = :id LIMIT 1")
    LiveData<Estudiante> obtenerPorId(int id);

    // Nota: No devuelve LiveData porque lo llamaremos bajo demanda.
    @Query("SELECT * FROM estudiantes WHERE nro_documento = :nroDocumento LIMIT 1")
    Estudiante obtenerPorDocumento(String nroDocumento);
}
