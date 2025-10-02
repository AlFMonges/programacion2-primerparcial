package com.examenparcial1.universidadapp.ui.fragments;

import android.app.AlertDialog; // Importar AlertDialog
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast; // Importar Toast

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.examenparcial1.universidadapp.R;
import com.examenparcial1.universidadapp.data.relations.InscripcionDetalle;
import com.examenparcial1.universidadapp.databinding.FragmentInscripcionListBinding;
import com.examenparcial1.universidadapp.ui.adapters.InscripcionAdapter;
import com.examenparcial1.universidadapp.ui.viewmodel.InscripcionViewModel;

// 1. Implementar la interfaz del adaptador
public class InscripcionListFragment extends Fragment implements InscripcionAdapter.OnInscripcionActionListener {

    private FragmentInscripcionListBinding binding;
    private InscripcionViewModel viewModel;
    private InscripcionAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentInscripcionListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        adapter = new InscripcionAdapter();
        adapter.setListener(this); // 2. Asignar 'this' como el listener
        binding.recyclerInscripciones.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerInscripciones.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(InscripcionViewModel.class);

        viewModel.getInscripcionesConDetalles().observe(getViewLifecycleOwner(), inscripcionesConDetalles -> {
            if (inscripcionesConDetalles != null) {
                adapter.setLista(inscripcionesConDetalles);
            }
        });

        binding.fabAddInscripcion.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_inscripcionList_to_inscripcionForm);
        });
    }

    // 3. Implementar el método de la interfaz
    @Override
    public void onEliminarClick(InscripcionDetalle inscripcionDetalle) {
        new AlertDialog.Builder(requireContext())
                .setTitle("Eliminar Inscripción")
                .setMessage("¿Estás seguro de que deseas eliminar la inscripción de " +
                        inscripcionDetalle.nombreEstudiante + " en el curso " +
                        inscripcionDetalle.nombreCurso + "?")
                .setPositiveButton(android.R.string.yes, (dialog, which) -> {
                    // El método eliminar del ViewModel espera un objeto Inscripcion
                    viewModel.eliminar(inscripcionDetalle.inscripcion);
                    Toast.makeText(getContext(), "Inscripción eliminada", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton(android.R.string.no, null)
                .show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
