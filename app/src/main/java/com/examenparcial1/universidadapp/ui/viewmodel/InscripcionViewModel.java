package com.examenparcial1.universidadapp.ui.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.examenparcial1.universidadapp.data.entities.Inscripcion;
import com.examenparcial1.universidadapp.data.repository.InscripcionRepository;

import java.util.List;

public class InscripcionViewModel extends AndroidViewModel {

    private InscripcionRepository repository;
    private LiveData<List<Inscripcion>> inscripciones;

    public InscripcionViewModel(@NonNull Application application) {
        super(application);
        repository = new InscripcionRepository(application);
        inscripciones = repository.obtenerInscripciones();
    }

    public LiveData<List<Inscripcion>> getInscripciones() {
        return inscripciones;
    }

    public void insertar(Inscripcion inscripcion) {
        repository.insertar(inscripcion);
    }

    public void actualizar(Inscripcion inscripcion) {
        repository.actualizar(inscripcion);
    }

    public void eliminar(Inscripcion inscripcion) {
        repository.eliminar(inscripcion);
    }
}

