package com.example.health4u;

import android.view.ViewGroup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;




public class listaCitasAdapter extends RecyclerView.Adapter<listaCitasAdapter.ContactoViewHolder> {

    ArrayList<Citas> listaCitas;
    public listaCitasAdapter(ArrayList<Citas> listaCitas){
        this.listaCitas=listaCitas;
    }

    @NonNull
    @Override

    public listaCitasAdapter.ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_citas, null, false);
        return new listaCitasAdapter.ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull listaCitasAdapter.ContactoViewHolder holder, int position) {

        holder.viewNombreCita.setText(" "+listaCitas.get(position).getNombreCita());
        holder.viewFechaCita.setText(" Fecha: "+ (listaCitas.get(position).getFechaCita()));
        holder.viewHoraCita.setText(" Hora: "+(listaCitas.get(position).getHoraCita()));
        holder.viewDescripcionCita.setText(" Descripcion: "+(listaCitas.get(position).getDescripcionCita()));
        holder.viewDireccionCita.setText(" Direccion: "+(listaCitas.get(position).getDireccionCita()));

    }

    @Override
    public int getItemCount() {
        return listaCitas.size();

    }



    public class ContactoViewHolder extends RecyclerView.ViewHolder {
        TextView viewNombreCita, viewFechaCita, viewHoraCita, viewDescripcionCita, viewDireccionCita;
        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombreCita = itemView.findViewById(R.id.viewNombreCita);
            viewFechaCita = itemView.findViewById(R.id.viewFechaCita);
            viewHoraCita = itemView.findViewById(R.id.viewHoraCita);
            viewDescripcionCita = itemView.findViewById(R.id.viewDecripcionCita);
            viewDireccionCita= itemView.findViewById(R.id.viewDireccionCita);
        }
    }
}
