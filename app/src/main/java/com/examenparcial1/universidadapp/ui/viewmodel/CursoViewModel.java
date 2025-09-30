package com.examenparcial1.universidadapp.ui.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.examenparcial1.universidadapp.data.entities.Curso;
import com.examenparcial1.universidadapp.data.repository.CursoRepository;
import java.util.List;

public class CursoViewModel extends AndroidViewModel {

    private CursoRepository repository;
    private LiveData<List<Curso>> listaCursos;

    public CursoViewModel(@NonNull Application application) {
        super(application);
        repository = new CursoRepository(application);
        listaCursos = repository.obtenerTodos();
    }

    public LiveData<List<Curso>> getListaCursos() {
        return listaCursos;
    }

}
