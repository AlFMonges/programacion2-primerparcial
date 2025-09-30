package com.examenparcial1.universidadapp.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.examenparcial1.universidadapp.data.entities.Profesor;
import com.examenparcial1.universidadapp.databinding.ItemProfesorBinding; // Asumiendo que esta clase de binding se generará
import java.util.ArrayList;
import java.util.List;

public class ProfesorAdapter extends RecyclerView.Adapter<ProfesorAdapter.ViewHolder> {

    private List<Profesor> listaProfesores = new ArrayList<>();

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
        holder.binding.tvNombreProfesor.setText(profesor.nombres + " " + profesor.apellidos);
        holder.binding.tvEmailProfesor.setText(profesor.email);
        // Si tienes más campos en item_profesor.xml, puedes enlazarlos aquí.
        // Por ejemplo, si tuvieras un TextView para el teléfono:
        // holder.binding.tvTelefonoProfesor.setText(profesor.telefono);
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
    }
}
