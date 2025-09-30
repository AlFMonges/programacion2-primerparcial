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

public class EstudianteFormFragment extends Fragment {

    private FragmentEstudianteFormBinding binding;
    private EstudianteViewModel viewModel;

    public EstudianteFormFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEstudianteFormBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        viewModel = new ViewModelProvider(this).get(EstudianteViewModel.class);

        binding.btnGuardarEstudiante.setOnClickListener(v -> guardarEstudiante());

        return view;
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

        Estudiante nuevoEstudiante = new Estudiante(nroDocumento, nombres, apellidos, telefono, email);
        viewModel.insertar(nuevoEstudiante);

        Toast.makeText(getContext(), "Estudiante guardado", Toast.LENGTH_SHORT).show();
        // Navegar de regreso a la lista de estudiantes
        Navigation.findNavController(requireView()).popBackStack();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
