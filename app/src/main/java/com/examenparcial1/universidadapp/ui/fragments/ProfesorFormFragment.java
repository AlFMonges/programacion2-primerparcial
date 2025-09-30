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
import com.examenparcial1.universidadapp.data.entities.Profesor;
import com.examenparcial1.universidadapp.databinding.FragmentProfesorFormBinding; // Asumiendo este nombre para el binding
import com.examenparcial1.universidadapp.ui.viewmodel.ProfesorViewModel;
import org.jspecify.annotations.NonNull;

public class ProfesorFormFragment extends Fragment {

    private FragmentProfesorFormBinding binding;
    private ProfesorViewModel viewModel;

    public ProfesorFormFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfesorFormBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        viewModel = new ViewModelProvider(this).get(ProfesorViewModel.class);

        binding.btnGuardarProfesor.setOnClickListener(v -> guardarProfesor());

        return view;
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

        Profesor nuevoProfesor = new Profesor(nombres, apellidos, telefono, email);
        viewModel.insertar(nuevoProfesor);

        Toast.makeText(getContext(), "Profesor guardado", Toast.LENGTH_SHORT).show();
        // Navegar de regreso a la lista de profesores
        Navigation.findNavController(requireView()).popBackStack();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
