package com.example.tpfinalmobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class CronogramaAdapter(private val items: List<CronogramaItem>) :
    RecyclerView.Adapter<CronogramaAdapter.CronogramaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CronogramaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cronograma, parent, false)
        return CronogramaViewHolder(view)
    }

    override fun onBindViewHolder(holder: CronogramaViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class CronogramaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val horarioText: TextView = view.findViewById(R.id.itemHorario)
        private val materiaText: TextView = view.findViewById(R.id.itemMateria)
        private val modoText: TextView = view.findViewById(R.id.itemModo)
        private val modoIndicator: View = view.findViewById(R.id.itemModoIndicator)

        fun bind(item: CronogramaItem) {
            horarioText.text = item.horario
            materiaText.text = item.materia
            modoText.text = item.modo

            //  color del indicador segun el modo
            val colorRes = if (item.modo == "Virtual") R.color.success else R.color.yellow
            modoIndicator.setBackgroundColor(
                ContextCompat.getColor(itemView.context, colorRes)
            )
        }
    }
}
