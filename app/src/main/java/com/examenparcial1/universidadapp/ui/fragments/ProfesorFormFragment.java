package com.examenparcial1.universidadapp.ui.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.examenparcial1.universidadapp.data.entities.Profesor;
import com.examenparcial1.universidadapp.databinding.FragmentProfesorFormBinding;
import com.examenparcial1.universidadapp.ui.viewmodel.ProfesorViewModel;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class ProfesorFormFragment extends Fragment {

    private FragmentProfesorFormBinding binding;
    private ProfesorViewModel viewModel;
    private int profesorId = -1; // Variable para almacenar el ID del profesor a editar
    private Profesor profesorActual = null; // Variable para almacenar el profesor actual

    public ProfesorFormFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            profesorId = getArguments().getInt("profesorId", -1);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfesorFormBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        viewModel = new ViewModelProvider(this).get(ProfesorViewModel.class);

        if (profesorId != -1) {
            // Estamos en modo edición, observar el profesor para llenar el formulario
            viewModel.obtenerPorId(profesorId).observe(getViewLifecycleOwner(), profesor -> {
                if (profesor != null) {
                    profesorActual = profesor;
                    llenarFormulario(profesor);
                }
            });
        }

        binding.btnGuardarProfesor.setOnClickListener(v -> guardarProfesor());

        return view;
    }

    private void llenarFormulario(Profesor profesor) {
        binding.etNombresProfesor.setText(profesor.nombres);
        binding.etApellidosProfesor.setText(profesor.apellidos);
        binding.etTelefonoProfesor.setText(profesor.telefono);
        binding.etEmailProfesor.setText(profesor.email);
    }

    private void guardarProfesor() {
        String nombres = binding.etNombresProfesor.getText().toString().trim();
        String apellidos = binding.etApellidosProfesor.getText().toString().trim();
        String telefono = binding.etTelefonoProfesor.getText().toString().trim();
        String email = binding.etEmailProfesor.getText().toString().trim();

        if (TextUtils.isEmpty(nombres) || TextUtils.isEmpty(apellidos) ||
            TextUtils.isEmpty(telefono) || TextUtils.isEmpty(email)) {
            Toast.makeText(getContext(), "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        if (profesorActual != null && profesorId != -1) {
            // Actualizar profesor existente
            profesorActual.nombres = nombres;
            profesorActual.apellidos = apellidos;
            profesorActual.telefono = telefono;
            profesorActual.email = email;
            viewModel.actualizar(profesorActual);
            Toast.makeText(getContext(), "Profesor actualizado", Toast.LENGTH_SHORT).show();
        } else {
            // Crear nuevo profesor
            Profesor nuevoProfesor = new Profesor(nombres, apellidos, telefono, email);
            viewModel.insertar(nuevoProfesor);
            Toast.makeText(getContext(), "Profesor guardado", Toast.LENGTH_SHORT).show();
        }

        // Navegar de regreso a la lista de profesores
        Navigation.findNavController(requireView()).popBackStack();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
