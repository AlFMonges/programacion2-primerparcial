package com.examenparcial1.universidadapp.data.repository;


import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.examenparcial1.universidadapp.data.AppDatabase;
import com.examenparcial1.universidadapp.data.dao.InscripcionDao;
import com.examenparcial1.universidadapp.data.entities.Inscripcion;
import com.examenparcial1.universidadapp.data.relations.InscripcionDetalle; // Importar el POJO

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InscripcionRepository {

    private InscripcionDao inscripcionDao;
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public InscripcionRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        inscripcionDao = db.inscripcionDao();
    }

    public LiveData<List<InscripcionDetalle>> obtenerInscripcionesConDetalles() {
        return inscripcionDao.obtenerInscripcionesConDetalles();
    }

    public LiveData<Inscripcion> obtenerPorIds(int estudianteId, int cursoId) {
        MutableLiveData<Inscripcion> resultado = new MutableLiveData<>();
        executor.execute(() -> {
            Inscripcion inscripcion = inscripcionDao.obtenerPorIds(estudianteId, cursoId);
            resultado.postValue(inscripcion);
        });
        return resultado;
    }

    public void insertar(Inscripcion inscripcion) {
        executor.execute(() -> inscripcionDao.insertar(inscripcion));
    }

    public void actualizar(Inscripcion inscripcion) {
        executor.execute(() -> inscripcionDao.actualizar(inscripcion));
    }

    public void eliminar(Inscripcion inscripcion) {
        executor.execute(() -> inscripcionDao.eliminar(inscripcion));
    }
}

