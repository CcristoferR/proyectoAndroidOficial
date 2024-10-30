package com.proyectoandroid.pruebafirebase;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TareaAdapter extends RecyclerView.Adapter<TareaAdapter.TareaViewHolder> {
    private List<Tarea> listaTareas;

    public TareaAdapter(List<Tarea> listaTareas) {
        this.listaTareas = listaTareas;
    }

    @NonNull
    @Override
    public TareaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tarea, parent, false);
        return new TareaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TareaViewHolder holder, int position) {
        Tarea tarea = listaTareas.get(position);
        holder.tituloTextView.setText(tarea.getTitulo());
        holder.descripcionTextView.setText(tarea.getDescripcion());
        holder.fechaTextView.setText(tarea.getFechaLimite());
        holder.prioridadTextView.setText(tarea.getPrioridad()); // Asegúrate de que este campo exista en tu modelo
    }

    @Override
    public int getItemCount() {
        return listaTareas.size();
    }

    public static class TareaViewHolder extends RecyclerView.ViewHolder {
        TextView tituloTextView;
        TextView descripcionTextView;
        TextView fechaTextView;
        TextView prioridadTextView;

        public TareaViewHolder(View itemView) {
            super(itemView);
            tituloTextView = itemView.findViewById(R.id.textViewTituloTarea); // Cambiado
            descripcionTextView = itemView.findViewById(R.id.textViewDescripcionTarea); // Cambiado
            fechaTextView = itemView.findViewById(R.id.textViewFechaTarea); // Cambiado
            prioridadTextView = itemView.findViewById(R.id.textViewPrioridad); // Asegúrate de que este ID exista
        }
    }
}
