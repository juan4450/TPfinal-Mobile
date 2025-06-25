package com.example.tpfinalmobile

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class MateriaAdapter(
    private val materias: List<Materia>,
    private val context: Context,
    private val usuario: String  // ✅ Coma agregada y parámetro incluido
) : RecyclerView.Adapter<MateriaAdapter.MateriaViewHolder>() {

    private val coloresCabecera = listOf(
        R.color.colorMateria1,
        R.color.colorMateria2,
        R.color.colorMateria3,
        R.color.colorMateria4
    )

    class MateriaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitulo: TextView = itemView.findViewById(R.id.txtTitulo)
        val txtVencimientos: TextView = itemView.findViewById(R.id.txtVencimientos)
        val txtTp: TextView = itemView.findViewById(R.id.txtTp)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MateriaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_materia, parent, false)
        return MateriaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MateriaViewHolder, position: Int) {
        val materia = materias[position]

        holder.txtTitulo.text = materia.nombre
        holder.txtVencimientos.text = "Vencimiento: ${materia.vencimiento}"
        holder.txtTp.text = materia.tp

        val colorRes = coloresCabecera[position % coloresCabecera.size]
        val color = ContextCompat.getColor(context, colorRes)
        holder.txtTitulo.setBackgroundColor(color)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetalleMateriaActivity::class.java).apply {
                putExtra("materia", materia)
                putExtra("usuario", usuario)  // ✅ El usuario ahora se pasa correctamente
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = materias.size
}
