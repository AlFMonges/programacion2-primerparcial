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
    public EstudianteAdapter(List<Estudiante> lista) {
        this.lista = lista;
    }


    private List<Estudiante> lista = new ArrayList<>();

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
        holder.binding.tvNombre.setText(e.nombres + " " + e.apellidos);
        holder.binding.tvDocumento.setText(e.nroDocumento);
        holder.binding.tvTelefono.setText(e.telefono);
        holder.binding.tvEmail.setText(e.email);
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
    }
}
