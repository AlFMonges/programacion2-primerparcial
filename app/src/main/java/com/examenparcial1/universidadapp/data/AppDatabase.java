package com.examenparcial1.universidadapp.data;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.examenparcial1.universidadapp.data.dao.CursoDao;
import com.examenparcial1.universidadapp.data.dao.EstudianteDao;
import com.examenparcial1.universidadapp.data.dao.InscripcionDao;
import com.examenparcial1.universidadapp.data.dao.ProfesorDao;
import com.examenparcial1.universidadapp.data.entities.Curso;
import com.examenparcial1.universidadapp.data.entities.Estudiante;
import com.examenparcial1.universidadapp.data.entities.Inscripcion;
import com.examenparcial1.universidadapp.data.entities.Profesor;
import com.examenparcial1.universidadapp.utils.Converters;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Estudiante.class, Profesor.class, Curso.class, Inscripcion.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    private static volatile AppDatabase INSTANCE;
    private static final ExecutorService databaseWriteExecutor = Executors.newSingleThreadExecutor();

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
                            .addCallback(roomDatabaseCallback) // Añadir el callback aquí
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // Usar el ExecutorService para insertar los datos en un hilo de fondo
            databaseWriteExecutor.execute(() -> {
                ProfesorDao profesorDao = INSTANCE.profesorDao();
                CursoDao dao = INSTANCE.cursoDao();
                //Se insertan datos de Profesores por defecto
                profesorDao.insertar(new Profesor("Milciades", "González","0982960792","mgonzalez@profesor.com"));
                profesorDao.insertar(new Profesor("Luis", "Morel","0984213987","lmorel@profesor.com"));
                profesorDao.insertar(new Profesor("Karina", "Obregón","0982929338","kariobregon@profesor.com"));
                profesorDao.insertar(new Profesor("Gustavo", "Quiñónez","0971543345","gquinonez@profesor.com"));
                profesorDao.insertar(new Profesor("Marcos", "Moral","0982652285","marcosmoral@profesor.com"));
                profesorDao.insertar(new Profesor("María Auxiliadora", "López","0985689599","mauxilopez@profesor.com"));

                //Luego se insertan datos de cursos para obtener profesores
                dao.insertar(new Curso("CS101", "Introducción a la Programación", 4, 3));
                dao.insertar(new Curso("CS201", "Estructuras de Datos", 4, 6));
                dao.insertar(new Curso("CS301", "Sistemas y Redes", 4, 2));
                dao.insertar(new Curso("CS350", "Sistemas Operativos", 4, 4));
                dao.insertar(new Curso("DB101", "Bases de Datos", 3, 5));
                dao.insertar(new Curso("WEB101", "Desarrollo Web: Frontend", 3, 1));
            });
        }
    };
}
