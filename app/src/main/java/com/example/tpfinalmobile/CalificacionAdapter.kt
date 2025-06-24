package com.example.tpfinalmobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CalificacionAdapter(private val listaCalificacion: List<CalificacionItem>) :
    RecyclerView.Adapter<CalificacionAdapter.CalificacionViewHolder>() {

    class CalificacionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombre: TextView = itemView.findViewById(R.id.tvNombre)
        val tvNota: TextView = itemView.findViewById(R.id.tvNota)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalificacionViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_calificacion, parent, false)
        return CalificacionViewHolder(view)
    }

    override fun onBindViewHolder(holder: CalificacionViewHolder, position: Int) {
        val item = listaCalificacion[position]
        holder.tvNombre.text = item.nombre
        holder.tvNota.text = item.nota
    }

    override fun getItemCount(): Int = listaCalificacion.size
}