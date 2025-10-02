package com.examenparcial1.universidadapp.ui.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.examenparcial1.universidadapp.data.entities.Estudiante;
import com.examenparcial1.universidadapp.databinding.FragmentEstudianteFormBinding;
import com.examenparcial1.universidadapp.ui.viewmodel.EstudianteViewModel;
import org.jspecify.annotations.NonNull;
import org.jspecify.annotations.Nullable;

public class EstudianteFormFragment extends Fragment {

    private FragmentEstudianteFormBinding binding;
    private EstudianteViewModel viewModel;
    private int estudianteId = -1; // Variable para almacenar el ID del estudiante a editar
    private Estudiante estudianteActual = null; // Variable para almacenar el estudiante actual

    public EstudianteFormFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            estudianteId = getArguments().getInt("estudianteId", -1);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEstudianteFormBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        viewModel = new ViewModelProvider(this).get(EstudianteViewModel.class);

        if (estudianteId != -1) {
            // Estamos en modo edición, observar el estudiante para llenar el formulario
            viewModel.obtenerPorId(estudianteId).observe(getViewLifecycleOwner(), estudiante -> {
                if (estudiante != null) {
                    estudianteActual = estudiante;
                    llenarFormulario(estudiante);
                }
            });
        }

        binding.btnGuardarEstudiante.setOnClickListener(v -> guardarEstudiante());

        return view;
    }

    private void llenarFormulario(Estudiante estudiante) {
        binding.etNroDocumento.setText(estudiante.nroDocumento);
        binding.etNombres.setText(estudiante.nombres);
        binding.etApellidos.setText(estudiante.apellidos);
        binding.etTelefono.setText(estudiante.telefono);
        binding.etEmail.setText(estudiante.email);
    }

    private void guardarEstudiante() {
        String nroDocumento = binding.etNroDocumento.getText().toString().trim();
        String nombres = binding.etNombres.getText().toString().trim();
        String apellidos = binding.etApellidos.getText().toString().trim();
        String telefono = binding.etTelefono.getText().toString().trim();
        String email = binding.etEmail.getText().toString().trim();

        if (TextUtils.isEmpty(nroDocumento) || TextUtils.isEmpty(nombres) ||
            TextUtils.isEmpty(apellidos) || TextUtils.isEmpty(telefono) || TextUtils.isEmpty(email)) {
            Toast.makeText(getContext(), "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show();
            return;
        }

        if (estudianteActual != null && estudianteId != -1) {
            // Actualizar estudiante existente
            estudianteActual.nroDocumento = nroDocumento;
            estudianteActual.nombres = nombres;
            estudianteActual.apellidos = apellidos;
            estudianteActual.telefono = telefono;
            estudianteActual.email = email;
            viewModel.actualizar(estudianteActual);
            Toast.makeText(getContext(), "Estudiante actualizado", Toast.LENGTH_SHORT).show();
        } else {
            // Crear nuevo estudiante
            Estudiante nuevoEstudiante = new Estudiante(nroDocumento, nombres, apellidos, telefono, email);
            viewModel.insertar(nuevoEstudiante);
            Toast.makeText(getContext(), "Estudiante guardado", Toast.LENGTH_SHORT).show();
        }

        // Navegar de regreso a la lista de estudiantes
        Navigation.findNavController(requireView()).popBackStack();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
