package com.examenparcial1.universidadapp.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.examenparcial1.universidadapp.data.relations.CursoConProfesor;
import com.examenparcial1.universidadapp.databinding.FragmentCursoListBinding;
import com.examenparcial1.universidadapp.ui.adapters.CursoAdapter;
import com.examenparcial1.universidadapp.ui.viewmodel.CursoViewModel;

public class CursoListFragment extends Fragment implements CursoAdapter.OnCursoClickListener {

    public static final String REQUEST_KEY = "curso_request";
    public static final String KEY_CURSO_ID = "curso_id";

    private FragmentCursoListBinding binding;
    private CursoViewModel viewModel;
    private CursoAdapter adapter;
    private boolean isSelectionMode = false; // Variable para guardar el modo

    public CursoListFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCursoListBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Verifica el modo en el que se está accediendo a la lista de cursos
        if (getArguments() != null) {
            isSelectionMode = getArguments().getBoolean("isSelectionMode", false);
        }

        adapter = new CursoAdapter();
        adapter.setOnCursoClickListener(this);
        binding.recyclerCursos.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerCursos.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(CursoViewModel.class);

        viewModel.getListaCursosConProfesor().observe(getViewLifecycleOwner(), cursosConProfesor -> {
            if (cursosConProfesor != null) {
                adapter.setLista(cursosConProfesor);
            }
        });
    }

    @Override
    public void onCursoClick(CursoConProfesor curso) {
        // Solo actúa si está en modo selección de curso
        if (isSelectionMode) {
            Bundle result = new Bundle();
            result.putInt(KEY_CURSO_ID, curso.curso.id);
            getParentFragmentManager().setFragmentResult(REQUEST_KEY, result);
            Navigation.findNavController(requireView()).popBackStack();
        }
        // Si no estamos en modo selección, no hacer nada.
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
