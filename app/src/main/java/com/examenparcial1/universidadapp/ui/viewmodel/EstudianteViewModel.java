package com.examenparcial1.universidadapp.ui.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.examenparcial1.universidadapp.data.entities.Estudiante;
import com.examenparcial1.universidadapp.data.repository.EstudianteRepository;

import org.jspecify.annotations.NonNull;

import java.util.List;

public class EstudianteViewModel extends AndroidViewModel {
    private final EstudianteRepository repo;
    private final LiveData<List<Estudiante>> listaEstudiantes;

    public EstudianteViewModel(@NonNull Application app) {
        super(app);
        repo = new EstudianteRepository(app);
        listaEstudiantes = repo.obtenerTodos();
    }

    public LiveData<List<Estudiante>> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public void insertar(Estudiante e) { repo.insertar(e); }
    public void actualizar(Estudiante e) { repo.actualizar(e); }
    public void eliminar(Estudiante e) { repo.eliminar(e); }
    public LiveData<Estudiante> obtenerPorId(int id) { return repo.obtenerPorId(id); }

}
