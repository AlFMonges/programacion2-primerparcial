package com.examenparcial1.universidadapp.data.repository;

import android.app.Application;
import android.content.Context;
import androidx.lifecycle.LiveData;
import com.examenparcial1.universidadapp.data.AppDatabase;
import com.examenparcial1.universidadapp.data.dao.EstudianteDao;
import com.examenparcial1.universidadapp.data.entities.Estudiante;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EstudianteRepository {
    private final EstudianteDao dao;
    private final Executor executor = Executors.newSingleThreadExecutor();

    public EstudianteRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        dao = db.estudianteDao();
    }

    public LiveData<List<Estudiante>> obtenerTodos() {
        return dao.obtenerTodos();
    }

    public LiveData<Estudiante> obtenerPorId(int id) {
        return dao.obtenerPorId(id);
    }

    public void insertar(final Estudiante e) {
        executor.execute(() -> dao.insertar(e));
    }

    public void actualizar(final Estudiante e) {
        executor.execute(() -> dao.actualizar(e));
    }

    public void eliminar(final Estudiante e) {
        executor.execute(() -> dao.eliminar(e));
    }
}
