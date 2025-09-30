package com.examenparcial1.universidadapp.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.examenparcial1.universidadapp.data.entities.Curso;

import java.util.List;
@Dao
public interface CursoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertar(Curso curso);

    @Update
    void actualizar(Curso curso);

    @Delete
    void eliminar(Curso curso);

    @Query("SELECT * FROM cursos ORDER BY nombre ASC")
    LiveData<List<Curso>> obtenerTodos();
}
