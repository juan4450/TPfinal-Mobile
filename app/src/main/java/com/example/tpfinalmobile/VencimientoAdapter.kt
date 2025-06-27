package com.example.tpfinalmobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

class VencimientosAdapter(
    private val materias: List<Materia>,
    private val onItemClick: (Materia) -> Unit
) : RecyclerView.Adapter<VencimientosAdapter.VencimientoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VencimientoViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_vencimiento, parent, false)
        return VencimientoViewHolder(view)
    }

    override fun onBindViewHolder(holder: VencimientoViewHolder, position: Int) {
        val materia = materias[position]
        holder.bind(materia)
        holder.itemView.setOnClickListener {
            onItemClick(materia)
        }
    }

    override fun getItemCount(): Int = materias.size

    inner class VencimientoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val header: View = itemView.findViewById(R.id.headerVencimiento)
        private val nombreMateria: TextView = itemView.findViewById(R.id.tvNombreMateria)
        private val fecha: TextView = itemView.findViewById(R.id.tvFecha)
        private val tp: TextView = itemView.findViewById(R.id.tvTp)

        fun bind(materia: Materia) {
            nombreMateria.text = materia.nombre
            fecha.text = materia.vencimiento
            tp.text = materia.tp

            val colorResId = when (materia.nombre) {
                "App. para dispositivos Móviles" -> R.color.colorMateria1
                "Metodología de Prueba de Sistemas" -> R.color.colorMateria2
                "Práctica Profesional II" -> R.color.colorMateria3
                "Tecnologías de la Información" -> R.color.colorMateria4
                else -> R.color.colorPrimary
            }


            val color = ContextCompat.getColor(itemView.context, colorResId)
            header.setBackgroundColor(color)
        }
    }
}

