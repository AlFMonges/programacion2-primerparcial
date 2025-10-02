package com.examenparcial1.universidadapp.ui.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.examenparcial1.universidadapp.data.entities.Inscripcion;
import com.examenparcial1.universidadapp.data.relations.InscripcionDetalle; // Importar el POJO
import com.examenparcial1.universidadapp.data.repository.InscripcionRepository;

import java.util.List;

public class InscripcionViewModel extends AndroidViewModel {

    private InscripcionRepository repository;
    private LiveData<List<InscripcionDetalle>> inscripcionesConDetalles;

    public InscripcionViewModel(@NonNull Application application) {
        super(application);
        repository = new InscripcionRepository(application);
        inscripcionesConDetalles = repository.obtenerInscripcionesConDetalles();
    }

    public LiveData<List<InscripcionDetalle>> getInscripcionesConDetalles() {
        return inscripcionesConDetalles;
    }

    public LiveData<Inscripcion> obtenerPorIds(int estudianteId, int cursoId) {
        return repository.obtenerPorIds(estudianteId, cursoId);
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
