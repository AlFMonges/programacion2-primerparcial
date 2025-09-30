package com.examenparcial1.universidadapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.examenparcial1.universidadapp.data.entities.Curso;
import com.examenparcial1.universidadapp.databinding.ItemCursoBinding;

import java.util.ArrayList;
import java.util.List;

public class CursoAdapter extends RecyclerView.Adapter<CursoAdapter.ViewHolder> {

    private List<Curso> lista = new ArrayList<>();

    public void setLista(List<Curso> lista) {
        this.lista = lista;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCursoBinding binding = ItemCursoBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false
        );
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Curso c = lista.get(position);
        holder.binding.tvNombreCurso.setText(c.nombre);
        holder.binding.tvCodigoCurso.setText(c.codigo);
        holder.binding.tvProfesor.setText("Profesor ID: " + c.profesorId);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ItemCursoBinding binding;
        public ViewHolder(ItemCursoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

