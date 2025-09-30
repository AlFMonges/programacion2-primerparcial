package com.examenparcial1.universidadapp.ui.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.examenparcial1.universidadapp.databinding.FragmentInscripcionListBinding;
import com.examenparcial1.universidadapp.ui.adapters.InscripcionAdapter;
import com.examenparcial1.universidadapp.ui.viewmodel.InscripcionViewModel;

import org.jspecify.annotations.NonNull;

public class InscripcionListFragment extends Fragment {

    private FragmentInscripcionListBinding binding;
    private InscripcionViewModel viewModel;
    private InscripcionAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentInscripcionListBinding.inflate(inflater, container, false);

        adapter = new InscripcionAdapter();
        binding.recyclerInscripciones.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerInscripciones.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(InscripcionViewModel.class);
        viewModel.getInscripciones().observe(getViewLifecycleOwner(), inscripciones -> {
            if (inscripciones != null) {
                adapter.setLista(inscripciones);
            }
        });

        return binding.getRoot();
    }
}
