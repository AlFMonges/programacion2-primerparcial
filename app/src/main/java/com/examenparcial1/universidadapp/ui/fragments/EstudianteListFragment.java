package com.examenparcial1.universidadapp.ui.fragments;

import android.app.AlertDialog;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.examenparcial1.universidadapp.R;
import com.examenparcial1.universidadapp.data.entities.Estudiante;
import com.examenparcial1.universidadapp.databinding.FragmentEstudianteListBinding;
import com.examenparcial1.universidadapp.ui.adapters.EstudianteAdapter;
import com.examenparcial1.universidadapp.ui.viewmodel.EstudianteViewModel;
import org.jspecify.annotations.NonNull;

public class EstudianteListFragment extends Fragment implements EstudianteAdapter.OnEstudianteActionListener {

    private FragmentEstudianteListBinding binding;
    private EstudianteViewModel viewModel;
    private EstudianteAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEstudianteListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Inicializar el adaptador pasándole 'this' como listener
        adapter = new EstudianteAdapter(this);
        binding.recyclerEstudiantes.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerEstudiantes.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(EstudianteViewModel.class);
        viewModel.getListaEstudiantes().observe(getViewLifecycleOwner(), estudiantes -> {
            adapter.setLista(estudiantes);
        });

        binding.fabAddEstudiante.setOnClickListener(v -> {
            // Navegar para agregar un nuevo estudiante (sin pasar argumentos)
            Navigation.findNavController(v)
                    .navigate(R.id.action_estudianteList_to_estudianteForm);
        });

        return view;
    }

    @Override
    public void onEditarClick(Estudiante estudiante) {
        // Navegar al formulario para editar, pasando el ID del estudiante
        Bundle bundle = new Bundle();
        bundle.putInt("estudianteId", estudiante.id);
        Navigation.findNavController(requireView()).navigate(R.id.action_estudianteList_to_estudianteForm, bundle);
    }

    @Override
    public void onEliminarClick(Estudiante estudiante) {
        new AlertDialog.Builder(getContext())
                .setTitle("Eliminar Estudiante")
                .setMessage("¿Estás seguro de que deseas eliminar a " + estudiante.nombres + " " + estudiante.apellidos + "?")
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    viewModel.eliminar(estudiante);
                    Toast.makeText(getContext(), "Estudiante eliminado", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton(android.R.string.no, null)
                // .setIcon(android.R.drawable.ic_dialog_alert) // LÍNEA ELIMINADA
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
