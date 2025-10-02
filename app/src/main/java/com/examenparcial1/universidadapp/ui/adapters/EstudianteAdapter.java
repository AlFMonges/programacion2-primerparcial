package com.examenparcial1.universidadapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.examenparcial1.universidadapp.data.entities.Estudiante;
import com.examenparcial1.universidadapp.databinding.ItemEstudianteBinding;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class EstudianteAdapter extends RecyclerView.Adapter<EstudianteAdapter.ViewHolder> {

    private List<Estudiante> lista = new ArrayList<>();
    private OnEstudianteActionListener listener;

    // Interface para manejar los eventos de clic
    public interface OnEstudianteActionListener {
        void onEditarClick(Estudiante estudiante);
        void onEliminarClick(Estudiante estudiante);
    }

    public EstudianteAdapter(OnEstudianteActionListener listener) {
        this.listener = listener;
    }

    public void setLista(List<Estudiante> lista) {
        this.lista = lista;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemEstudianteBinding binding = ItemEstudianteBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Estudiante e = lista.get(position);
        holder.bind(e, listener);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemEstudianteBinding binding;

        public ViewHolder(ItemEstudianteBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(final Estudiante estudiante, final OnEstudianteActionListener listener) {
            binding.tvNombre.setText(estudiante.nombres + " " + estudiante.apellidos);
            binding.tvDocumento.setText(estudiante.nroDocumento);
            binding.tvTelefono.setText(estudiante.telefono);
            binding.tvEmail.setText(estudiante.email);

            binding.btnEditarEstudiante.setOnClickListener(v -> listener.onEditarClick(estudiante));
            binding.btnEliminarEstudiante.setOnClickListener(v -> listener.onEliminarClick(estudiante));
        }
    }
}
