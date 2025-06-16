package com.example.tpfinal_mobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CronogramaAdapter(private val listaCronograma: List<CronogramaItem>) :
    RecyclerView.Adapter<CronogramaAdapter.CronogramaViewHolder>() {

    class CronogramaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dia: TextView = itemView.findViewById(R.id.txtDia)
        val horario: TextView = itemView.findViewById(R.id.txtHorario)
        val materia: TextView = itemView.findViewById(R.id.txtMateria)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CronogramaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_cronograma, parent, false)
        return CronogramaViewHolder(view)
    }

    override fun onBindViewHolder(holder: CronogramaViewHolder, position: Int) {
        val item = listaCronograma[position]
        holder.dia.text = item.dia
        holder.horario.text = item.horario
        holder.materia.text = item.materia
    }

    override fun getItemCount(): Int = listaCronograma.size
}
