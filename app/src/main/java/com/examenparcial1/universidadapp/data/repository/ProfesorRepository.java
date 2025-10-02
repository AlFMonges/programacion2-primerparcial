package com.examenparcial1.universidadapp.data.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.examenparcial1.universidadapp.data.AppDatabase;
import com.examenparcial1.universidadapp.data.dao.ProfesorDao;
import com.examenparcial1.universidadapp.data.entities.Profesor;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProfesorRepository {

    private ProfesorDao profesorDao;
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public ProfesorRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        profesorDao = db.profesorDao();
    }

    public LiveData<List<Profesor>> obtenerProfesores() {
        return profesorDao.obtenerProfesores();
    }

    // Obtener un profesor por su ID
    public LiveData<Profesor> obtenerPorId(int profesorId) {
        return profesorDao.obtenerPorId(profesorId);
    }

    public void insertar(Profesor profesor) {
        executor.execute(() -> profesorDao.insertar(profesor));
    }

    public void actualizar(Profesor profesor) {
        executor.execute(() -> profesorDao.actualizar(profesor));
    }

    public void eliminar(Profesor profesor) {
        executor.execute(() -> profesorDao.eliminar(profesor));
    }
}
