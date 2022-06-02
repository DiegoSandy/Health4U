package com.example.health4u;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;




public class listaMedicamentosAdapter extends RecyclerView.Adapter<listaMedicamentosAdapter.ContactoViewHolder>  {
    ArrayList<Medicamentos> listaMedicamentos;
    public listaMedicamentosAdapter(ArrayList<Medicamentos> listaMedicamentos){
        this.listaMedicamentos=listaMedicamentos;
    }

    @NonNull
    @Override

    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_medicamento, null, false);
        return new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {

        holder.viewNombre.setText(""+listaMedicamentos.get(position).getNombre());
        holder.viewDosis.setText(" Dosis: "+ (listaMedicamentos.get(position).getDosis()) + " mg");
        holder.viewFechaIni.setText(" Fecha Inicio: "+(listaMedicamentos.get(position).getFechaIni()));
        holder.viewFechaFin.setText(" Fecha Fin: "+(listaMedicamentos.get(position).getFechaFin()));

    }

    @Override
    public int getItemCount() {
        return listaMedicamentos.size();

    }

    /*public listaMedicamentosAdapter(@NonNull View itemView) {
        super(itemView);
    }


    @NonNull
    @Override
    //El dise;o de cada elemnto de la lista
    public listaMedicamentosAdapter.ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_item_medicamento, null, false);
       return new ContactoViewHolder(view);
    }

    @Override
    //Asignar los elementos para cada opcion
    public void onBindViewHolder(@NonNull listaMedicamentosAdapter.ContactoViewHolder holder, int position) {
         holder.viewNombre.setText(listaMedicamentos.get(position).getNombre());
        holder.viewDosis.setText("Dosis: "+ (listaMedicamentos.get(position).getDosis()) + "mg");
        holder.viewFechaIni.setText("Fecha Inicio: "+(listaMedicamentos.get(position).getFechaIni()));
        holder.viewFechaFin.setText("Fecha Fin: "+(listaMedicamentos.get(position).getFechaFin()));
    }

    @Override
    //tama;o de nuestra lista
    public int getItemCount() {
        return listaMedicamentos.size();
    }

     */

    public class ContactoViewHolder extends RecyclerView.ViewHolder {
        TextView viewNombre, viewDosis, viewFechaIni, viewFechaFin;
        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);

            viewNombre = itemView.findViewById(R.id.viewNombre);
            viewDosis = itemView.findViewById(R.id.viewDosis);
            viewFechaIni = itemView.findViewById(R.id.viewFechaIni);
            viewFechaFin = itemView.findViewById(R.id.viewFechaFin);
        }
    }


    }



