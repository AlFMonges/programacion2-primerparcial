package com.examenparcial1.universidadapp.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.examenparcial1.universidadapp.data.entities.Inscripcion;
import com.examenparcial1.universidadapp.data.relations.InscripcionDetalle; // Importar el nuevo POJO

import java.util.List;
@Dao
public interface InscripcionDao {

    // Este método ya no se usará para la lista principal, pero se puede mantener.
    @Query("SELECT * FROM inscripciones ORDER BY fechaInscripcion DESC")
    LiveData<List<Inscripcion>> obtenerInscripciones();

    @Insert
    void insertar(Inscripcion inscripcion);

    @Update
    void actualizar(Inscripcion inscripcion);

    @Delete
    void eliminar(Inscripcion inscripcion);

    @Query("SELECT i.*, e.nombres || ' ' || e.apellidos AS nombreEstudiante, c.nombre AS nombreCurso " +
           "FROM inscripciones i " +
           "JOIN estudiantes e ON i.estudianteId = e.id " +
           "JOIN cursos c ON i.cursoId = c.id " +
           "ORDER BY i.fechaInscripcion DESC")
    LiveData<List<InscripcionDetalle>> obtenerInscripcionesConDetalles();

    // Metodo de verificación de inscripcion no repetida
    @Query("SELECT * FROM inscripciones WHERE estudianteId = :estudianteId AND cursoId = :cursoId LIMIT 1")
    Inscripcion obtenerPorIds(int estudianteId, int cursoId);
}
