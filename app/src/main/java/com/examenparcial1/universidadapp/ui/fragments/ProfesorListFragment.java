package com.examenparcial1.universidadapp.ui.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.examenparcial1.universidadapp.R; // Para la acción de navegación
import com.examenparcial1.universidadapp.databinding.FragmentProfesorListBinding; // Asumiendo este nombre para el binding
import com.examenparcial1.universidadapp.ui.adapters.ProfesorAdapter;
import com.examenparcial1.universidadapp.ui.viewmodel.ProfesorViewModel;
import org.jspecify.annotations.NonNull;

public class ProfesorListFragment extends Fragment {

    private FragmentProfesorListBinding binding;
    private ProfesorViewModel viewModel;
    private ProfesorAdapter adapter;

    public ProfesorListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfesorListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        adapter = new ProfesorAdapter();
        // Asegúrate que el ID del RecyclerView en fragment_profesor_list.xml es "recyclerProfesores"
        binding.recyclerProfesores.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerProfesores.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(ProfesorViewModel.class);
        viewModel.getListaProfesores().observe(getViewLifecycleOwner(), profesores -> {
            if (profesores != null) {
                adapter.setLista(profesores);
            }
        });

        // Asegúrate que el ID del FAB en fragment_profesor_list.xml es "fabAddProfesor"
        binding.fabAddProfesor.setOnClickListener(v -> {
            // Esta acción debe estar definida en tu nav_graph.xml
            Navigation.findNavController(v).navigate(R.id.action_profesorListFragment_to_profesorFormFragment);
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null; // Evitar memory leaks
    }
}
