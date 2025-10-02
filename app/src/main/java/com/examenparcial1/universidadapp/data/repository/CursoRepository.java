package com.examenparcial1.universidadapp.data.repository;

import android.app.Application;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.examenparcial1.universidadapp.data.AppDatabase;
import com.examenparcial1.universidadapp.data.dao.CursoDao;
import com.examenparcial1.universidadapp.data.entities.Curso;
import com.examenparcial1.universidadapp.data.relations.CursoConProfesor;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CursoRepository {

    private CursoDao cursoDao;
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public CursoRepository(Application application) {
        AppDatabase db = AppDatabase.getInstance(application);
        cursoDao = db.cursoDao();
    }

    public LiveData<List<CursoConProfesor>> obtenerCursosConProfesor() {
        return cursoDao.obtenerCursosConProfesor();
    }

    public LiveData<Curso> obtenerPorId(int cursoId) {
        MutableLiveData<Curso> resultado = new MutableLiveData<>();
        executor.execute(() -> {
            Curso curso = cursoDao.obtenerPorId(cursoId);
            resultado.postValue(curso);
        });
        return resultado;
    }

    public void insertar(Curso curso) {
        executor.execute(() -> cursoDao.insertar(curso));
    }

    public void actualizar(Curso curso) {
        executor.execute(() -> cursoDao.actualizar(curso));
    }

    public void eliminar(Curso curso) {
        executor.execute(() -> cursoDao.eliminar(curso));
    }
}
