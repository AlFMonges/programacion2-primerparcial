package com.examenparcial1.universidadapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.examenparcial1.universidadapp.data.entities.Profesor;
import com.examenparcial1.universidadapp.databinding.ItemProfesorBinding;
import java.util.ArrayList;
import java.util.List;

public class ProfesorAdapter extends RecyclerView.Adapter<ProfesorAdapter.ViewHolder> {

    private List<Profesor> listaProfesores = new ArrayList<>();
    private OnProfesorActionListener listener;

    // Interface para manejar los eventos de clic (solo editar)
    public interface OnProfesorActionListener {
        void onEditarClick(Profesor profesor);
    }

    public ProfesorAdapter(OnProfesorActionListener listener) {
        this.listener = listener;
    }

    public void setLista(List<Profesor> nuevaLista) {
        this.listaProfesores = nuevaLista;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemProfesorBinding binding = ItemProfesorBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Profesor profesor = listaProfesores.get(position);
        holder.bind(profesor, listener);
    }

    @Override
    public int getItemCount() {
        return listaProfesores.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemProfesorBinding binding;

        public ViewHolder(ItemProfesorBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(final Profesor profesor, final OnProfesorActionListener listener) {
            binding.tvNombreProfesor.setText(profesor.nombres + " " + profesor.apellidos);
            binding.tvEmailProfesor.setText("Correo: " + profesor.email);

            binding.btnEditarProfesor.setOnClickListener(v -> listener.onEditarClick(profesor));
        }
    }
}
