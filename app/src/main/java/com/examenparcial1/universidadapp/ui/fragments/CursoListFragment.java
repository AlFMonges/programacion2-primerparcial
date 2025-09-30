package com.examenparcial1.universidadapp.ui.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.examenparcial1.universidadapp.databinding.FragmentCursoListBinding;
import com.examenparcial1.universidadapp.ui.adapters.CursoAdapter;
import com.examenparcial1.universidadapp.ui.viewmodel.CursoViewModel;
import org.jspecify.annotations.NonNull;

public class CursoListFragment extends Fragment {

    private FragmentCursoListBinding binding;
    private CursoViewModel viewModel;
    private CursoAdapter adapter;

    public CursoListFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCursoListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        adapter = new CursoAdapter();
        binding.recyclerCursos.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerCursos.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(CursoViewModel.class);
        viewModel.getListaCursos().observe(getViewLifecycleOwner(), cursos -> {
            if (cursos != null) {
                adapter.setLista(cursos);
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
