package com.examenparcial1.universidadapp.ui.fragments;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.examenparcial1.universidadapp.R; // Importante
import com.examenparcial1.universidadapp.data.entities.Curso;
import com.examenparcial1.universidadapp.data.entities.Estudiante;
import com.examenparcial1.universidadapp.data.entities.Inscripcion;
import com.examenparcial1.universidadapp.databinding.FragmentInscripcionFormBinding;
import com.examenparcial1.universidadapp.ui.viewmodel.CursoViewModel;
import com.examenparcial1.universidadapp.ui.viewmodel.EstudianteViewModel;
import com.examenparcial1.universidadapp.ui.viewmodel.InscripcionViewModel;

import java.util.Date;

public class InscripcionFormFragment extends Fragment {

    private FragmentInscripcionFormBinding binding;
    private EstudianteViewModel estudianteViewModel;
    private CursoViewModel cursoViewModel; // Necesitamos el ViewModel de Curso
    private InscripcionViewModel inscripcionViewModel;

    private Estudiante estudianteSeleccionado = null;
    private Curso cursoSeleccionado = null; // Para guardar el curso seleccionado

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentInscripcionFormBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        estudianteViewModel = new ViewModelProvider(this).get(EstudianteViewModel.class);
        cursoViewModel = new ViewModelProvider(this).get(CursoViewModel.class);
        inscripcionViewModel = new ViewModelProvider(this).get(InscripcionViewModel.class);

        setupEventListeners();
        setupResultListener(); // Configurar el listener para el resultado del curso
    }

    private void setupEventListeners() {
        binding.etDocumentoEstudiante.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                String nroDocumento = binding.etDocumentoEstudiante.getText().toString().trim();
                if (!TextUtils.isEmpty(nroDocumento)) {
                    buscarEstudiante(nroDocumento);
                }
            }
        });

        // --- Navega a la lista de cursos para seleccionar una opción ---
        binding.tvCursoSeleccionado.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putBoolean("isSelectionMode", true);
            Navigation.findNavController(v).navigate(R.id.action_inscripcionFormFragment_to_cursoListFragment, bundle);
        });

        // --- Guarda la inscripción ---
        binding.btnGuardarInscripcion.setOnClickListener(v -> guardarInscripcion());
    }

    private void setupResultListener() {
        // Escuchar el resultado que envía CursoListFragment
        getParentFragmentManager().setFragmentResultListener(CursoListFragment.REQUEST_KEY, this, (requestKey, bundle) -> {
            int cursoId = bundle.getInt(CursoListFragment.KEY_CURSO_ID, -1);
            if (cursoId != -1) {
                // Si recibimos un ID válido, buscamos los detalles del curso
                cursoViewModel.obtenerPorId(cursoId).observe(getViewLifecycleOwner(), curso -> {
                    if (curso != null) {
                        cursoSeleccionado = curso;
                        binding.tvCursoSeleccionado.setText(curso.nombre);
                    }
                });
            }
        });
    }

    private void buscarEstudiante(String nroDocumento) {
        estudianteViewModel.obtenerPorDocumento(nroDocumento).observe(getViewLifecycleOwner(), estudiante -> {
            if (estudiante != null) {
                binding.tvNombreEstudiante.setText(estudiante.nombres + " " + estudiante.apellidos);
                estudianteSeleccionado = estudiante;
            } else {
                binding.tvNombreEstudiante.setText("Estudiante no encontrado");
                Toast.makeText(getContext(), "No se encontró un estudiante con ese documento", Toast.LENGTH_SHORT).show();
                estudianteSeleccionado = null;
            }
            estudianteViewModel.obtenerPorDocumento(nroDocumento).removeObservers(getViewLifecycleOwner());
        });
    }

    private void guardarInscripcion() {
        if (estudianteSeleccionado == null) {
            Toast.makeText(getContext(), "Por favor, busca y selecciona un estudiante válido", Toast.LENGTH_SHORT).show();
            return;
        }

        if (cursoSeleccionado == null) {
            Toast.makeText(getContext(), "Por favor, selecciona un curso", Toast.LENGTH_SHORT).show();
            return;
        }

        // verifica si existe o no la inscripción
        int idEstudiante = estudianteSeleccionado.id;
        int idCurso = cursoSeleccionado.id;

        inscripcionViewModel.obtenerPorIds(idEstudiante, idCurso).observe(getViewLifecycleOwner(), inscripcionExistente -> {
            if (inscripcionExistente != null) {
                // Si la inscripción ya existe, mostrar un mensaje y no hacer nada más
                Toast.makeText(getContext(), "Este estudiante ya está inscrito en este curso.", Toast.LENGTH_LONG).show();
            } else {
                // Si no existe, proceder con la inserción
                Inscripcion nuevaInscripcion = new Inscripcion(idEstudiante, idCurso, new Date());
                inscripcionViewModel.insertar(nuevaInscripcion);
                Toast.makeText(getContext(), "Inscripción guardada correctamente", Toast.LENGTH_LONG).show();
                Navigation.findNavController(requireView()).popBackStack();
            }
            // Dejar de observar para no recibir actualizaciones si algo cambia
            inscripcionViewModel.obtenerPorIds(idEstudiante, idCurso).removeObservers(getViewLifecycleOwner());
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
