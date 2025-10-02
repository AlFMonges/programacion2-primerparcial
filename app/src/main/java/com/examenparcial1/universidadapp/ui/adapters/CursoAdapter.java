package com.examenparcial1.universidadapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.View; // Importar View
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.examenparcial1.universidadapp.data.relations.CursoConProfesor;
import com.examenparcial1.universidadapp.databinding.ItemCursoBinding;

import java.util.ArrayList;
import java.util.List;

public class CursoAdapter extends RecyclerView.Adapter<CursoAdapter.ViewHolder> {

    private List<CursoConProfesor> lista = new ArrayList<>();
    private OnCursoClickListener listener; // Listener para los clics

    // --- INTERFAZ PARA EL CALLBACK ---
    public interface OnCursoClickListener {
        void onCursoClick(CursoConProfesor curso);
    }

    public void setOnCursoClickListener(OnCursoClickListener listener) {
        this.listener = listener;
    }

    public void setLista(List<CursoConProfesor> lista) {
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
        CursoConProfesor c = lista.get(position);
        // Pasamos el listener al ViewHolder
        holder.bind(c, listener);
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

        public void bind(final CursoConProfesor cursoConProfesor, final OnCursoClickListener listener) {
            binding.tvNombreCurso.setText(cursoConProfesor.curso.nombre);
            binding.tvCodigoCurso.setText(cursoConProfesor.curso.codigo);
            binding.tvProfesor.setText("Profesor: " + cursoConProfesor.nombreProfesor);

            // El metodo bind también recibe el listener
            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onCursoClick(cursoConProfesor);
                }
            });
        }
    }
}
