package com.examenparcial1.universidadapp.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.examenparcial1.universidadapp.data.dao.CursoDao;
import com.examenparcial1.universidadapp.data.dao.EstudianteDao;
import com.examenparcial1.universidadapp.data.dao.InscripcionDao;
import com.examenparcial1.universidadapp.data.dao.ProfesorDao;
import com.examenparcial1.universidadapp.data.entities.Curso;
import com.examenparcial1.universidadapp.data.entities.Estudiante;
import com.examenparcial1.universidadapp.data.entities.Inscripcion;
import com.examenparcial1.universidadapp.data.entities.Profesor;
import com.examenparcial1.universidadapp.utils.Converters;

@Database(entities = {Estudiante.class, Profesor.class, Curso.class, Inscripcion.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class}) // Anotación agregada
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;

    public abstract EstudianteDao estudianteDao();
    public abstract CursoDao cursoDao();
    public abstract ProfesorDao profesorDao();
    public abstract InscripcionDao inscripcionDao();

    public static AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    AppDatabase.class, "universidad_db")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
