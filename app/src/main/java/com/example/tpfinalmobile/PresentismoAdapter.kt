package com.example.tpfinalmobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tpfinalmobile.R
import com.example.tpfinalmobile.Asistencia

class PresentismoAdapter ( // Adapter para mostrar lista de objetos Asistencia en RecyclerView

    private val asistencias: List<Asistencia>
) : RecyclerView.Adapter<PresentismoAdapter.AsistenciaViewHolder>() {

    // ViewHolder que representa cada ítem individual del RecyclerView
    class AsistenciaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvFecha: TextView = itemView.findViewById(R.id.tvFecha)
        val ivAsistencia: ImageView = itemView.findViewById(R.id.ivAsistencia)
    }

    // Se llama cuando el RecyclerView necesita crear un nuevo ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsistenciaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_asistencia, parent, false)
        return AsistenciaViewHolder(view)
    }

    // Se llama para mostrar los datos en la posición especificada
    override fun onBindViewHolder(holder: AsistenciaViewHolder, position: Int) {
        val asistencia = asistencias[position]
        holder.tvFecha.text = asistencia.fecha

        if (asistencia.presente) {
            holder.ivAsistencia.setImageResource(R.drawable.ic_check)
            holder.ivAsistencia.setColorFilter(holder.itemView.context.getColor(R.color.success))
        } else {
            holder.ivAsistencia.setImageResource(R.drawable.ic_close)
            holder.ivAsistencia.setColorFilter(holder.itemView.context.getColor(R.color.error))
        }
    }

    // Devuelve la cantidad total de ítems a mostrar
    override fun getItemCount(): Int = asistencias.size
}
