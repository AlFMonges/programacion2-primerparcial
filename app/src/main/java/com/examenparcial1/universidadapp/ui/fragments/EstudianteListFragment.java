package com.examenparcial1.universidadapp.ui.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.examenparcial1.universidadapp.R;
import com.examenparcial1.universidadapp.databinding.FragmentEstudianteListBinding;
import com.examenparcial1.universidadapp.ui.adapters.EstudianteAdapter;
import com.examenparcial1.universidadapp.ui.viewmodel.EstudianteViewModel;
import org.jspecify.annotations.NonNull;
import java.util.ArrayList;
public class EstudianteListFragment extends Fragment {

    private FragmentEstudianteListBinding binding;
    private EstudianteViewModel viewModel;
    private EstudianteAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEstudianteListBinding.inflate(inflater, container, false);

        adapter = new EstudianteAdapter(new ArrayList<>());
        binding.recyclerEstudiantes.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerEstudiantes.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(EstudianteViewModel.class);
        viewModel.getListaEstudiantes().observe(getViewLifecycleOwner(), estudiantes -> {
            adapter.setLista(estudiantes);
        });

        binding.fabAddEstudiante.setOnClickListener(v -> {
            Navigation.findNavController(v)
                    .navigate(R.id.action_estudianteList_to_estudianteForm);
        });

        return binding.getRoot();
    }
}
