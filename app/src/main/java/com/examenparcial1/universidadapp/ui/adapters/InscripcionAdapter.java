package com.examenparcial1.universidadapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.examenparcial1.universidadapp.data.relations.InscripcionDetalle;
import com.examenparcial1.universidadapp.databinding.ItemInscripcionBinding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class InscripcionAdapter extends RecyclerView.Adapter<InscripcionAdapter.ViewHolder> {

    private List<InscripcionDetalle> lista = new ArrayList<>();
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
    private OnInscripcionActionListener listener; // Listener para la acción

    // --- INTERFAZ PARA EL CALLBACK ---
    public interface OnInscripcionActionListener {
        void onEliminarClick(InscripcionDetalle inscripcionDetalle);
    }

    public void setListener(OnInscripcionActionListener listener) {
        this.listener = listener;
    }

    public void setLista(List<InscripcionDetalle> lista) {
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
        InscripcionDetalle i = lista.get(position);
        holder.bind(i, dateFormat, listener);
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

        public void bind(final InscripcionDetalle inscripcionDetalle, SimpleDateFormat dateFormat, final OnInscripcionActionListener listener) {
            binding.tvEstudiante.setText("Estudiante: " + inscripcionDetalle.nombreEstudiante);
            binding.tvCurso.setText("Curso: " + inscripcionDetalle.nombreCurso);

            if (inscripcionDetalle.inscripcion.fechaInscripcion != null) {
                binding.tvFecha.setText("Fecha: " + dateFormat.format(inscripcionDetalle.inscripcion.fechaInscripcion));
            } else {
                binding.tvFecha.setText("Fecha: No especificada");
            }

            // El metodo bind también recibe el listener
            binding.btnEliminarInscripcion.setOnClickListener(v -> {
                if (listener != null) {
                    listener.onEliminarClick(inscripcionDetalle);
                }
            });
        }
    }
}
