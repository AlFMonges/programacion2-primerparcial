package com.examenparcial1.universidadapp.ui.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.examenparcial1.universidadapp.data.entities.Profesor;
import com.examenparcial1.universidadapp.data.repository.ProfesorRepository;
import java.util.List;

public class ProfesorViewModel extends AndroidViewModel {

    private ProfesorRepository repository;
    private LiveData<List<Profesor>> listaProfesores;

    public ProfesorViewModel(@NonNull Application application) {
        super(application);
        repository = new ProfesorRepository(application);
        listaProfesores = repository.obtenerProfesores();
    }

    public LiveData<List<Profesor>> getListaProfesores() {
        return listaProfesores;
    }

    // Metodo añadido para obtener un profesor por su ID
    public LiveData<Profesor> obtenerPorId(int profesorId) {
        return repository.obtenerPorId(profesorId);
    }

    public void insertar(Profesor profesor) {
        repository.insertar(profesor);
    }

    // Metodo para actualizar un profesor
    public void actualizar(Profesor profesor) {
        repository.actualizar(profesor);
    }

    // Metodo para eliminar un profesor
    public void eliminar(Profesor profesor) {
        repository.eliminar(profesor);
    }
}
