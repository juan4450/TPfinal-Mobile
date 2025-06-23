package com.example.tpfinalmobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class VencimientosAdapter(
    private val listaMaterias: List<Materia>
) : RecyclerView.Adapter<VencimientosAdapter.MateriaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MateriaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_vencimiento, parent, false)
        return MateriaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MateriaViewHolder, position: Int) {
        holder.bind(listaMaterias[position])
    }

    override fun getItemCount(): Int = listaMaterias.size

    inner class MateriaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvNombreMateria: TextView = itemView.findViewById(R.id.tvNombreMateria)
        private val tvTp: TextView = itemView.findViewById(R.id.tvTp)
        private val tvFecha: TextView = itemView.findViewById(R.id.tvFecha)

        fun bind(materia: Materia) {
            tvNombreMateria.text = materia.nombre
            tvTp.text = materia.tp
            tvFecha.text = materia.vencimiento
        }
    }
}
