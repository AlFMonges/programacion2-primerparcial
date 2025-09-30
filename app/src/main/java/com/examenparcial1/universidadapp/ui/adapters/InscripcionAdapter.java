package com.examenparcial1.universidadapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.examenparcial1.universidadapp.data.entities.Inscripcion;
import com.examenparcial1.universidadapp.databinding.ItemInscripcionBinding;

import java.util.ArrayList;
import java.util.List;

public class InscripcionAdapter extends RecyclerView.Adapter<InscripcionAdapter.ViewHolder> {

    private List<Inscripcion> lista = new ArrayList<>();

    public void setLista(List<Inscripcion> lista) {
        this.lista = lista;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemInscripcionBinding binding = ItemInscripcionBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Inscripcion i = lista.get(position);
        holder.binding.tvEstudiante.setText("Estudiante ID: " + i.estudianteId);
        holder.binding.tvCurso.setText("Curso ID: " + i.cursoId);
        holder.binding.tvFecha.setText("Fecha: " + i.fechaInscripcion);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemInscripcionBinding binding;
        public ViewHolder(ItemInscripcionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

