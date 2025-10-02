package com.examenparcial1.universidadapp.data.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData; // Importar MutableLiveData

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

    public LiveData<Estudiante> obtenerPorDocumento(String nroDocumento) {
        // Usamos MutableLiveData para poder postear el valor desde el hilo de fondo porque Room no permite consultas en el hilo proincipal
        MutableLiveData<Estudiante> resultado = new MutableLiveData<>();
        executor.execute(() -> {
            Estudiante estudiante = dao.obtenerPorDocumento(nroDocumento);
            resultado.postValue(estudiante);
        });
        return resultado;
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
