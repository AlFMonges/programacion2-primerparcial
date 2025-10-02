package com.examenparcial1.universidadapp.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.examenparcial1.universidadapp.R;
import com.examenparcial1.universidadapp.data.entities.Profesor;
import com.examenparcial1.universidadapp.databinding.FragmentProfesorListBinding;
import com.examenparcial1.universidadapp.ui.adapters.ProfesorAdapter;
import com.examenparcial1.universidadapp.ui.viewmodel.ProfesorViewModel;
import org.jspecify.annotations.NonNull;

public class ProfesorListFragment extends Fragment implements ProfesorAdapter.OnProfesorActionListener {

    private FragmentProfesorListBinding binding;
    private ProfesorViewModel viewModel;
    private ProfesorAdapter adapter;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfesorListBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        // Inicializar el adaptador pasándole 'this' como listener
        adapter = new ProfesorAdapter(this);
        binding.recyclerProfesores.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerProfesores.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(ProfesorViewModel.class);
        viewModel.getListaProfesores().observe(getViewLifecycleOwner(), profesores -> {
            if (profesores != null) {
                adapter.setLista(profesores);
            }
        });

        binding.fabAddProfesor.setOnClickListener(v -> {
            // Navegar para agregar un nuevo profesor (sin pasar argumentos)
            Navigation.findNavController(v)
                    .navigate(R.id.action_profesorListFragment_to_profesorFormFragment);
        });

        return view;
    }

    @Override
    public void onEditarClick(Profesor profesor) {
        // Navegar al formulario para editar, pasando el ID del profesor
        Bundle bundle = new Bundle();
        bundle.putInt("profesorId", profesor.id);
        Navigation.findNavController(requireView()).navigate(R.id.action_profesorListFragment_to_profesorFormFragment, bundle);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
