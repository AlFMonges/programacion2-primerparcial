package com.examenparcial1.universidadapp.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.examenparcial1.universidadapp.data.entities.Curso;
import com.examenparcial1.universidadapp.data.relations.CursoConProfesor;

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

    // Consulta con JOIN
    @Query("SELECT c.*, p.nombres || ' ' || p.apellidos AS nombreProfesor " +
           "FROM cursos c " +
           "JOIN profesores p ON c.profesorId = p.id " +
           "ORDER BY c.nombre ASC")
    LiveData<List<CursoConProfesor>> obtenerCursosConProfesor();

    @Query("SELECT * FROM cursos WHERE id = :cursoId LIMIT 1")
    Curso obtenerPorId(int cursoId);
}
