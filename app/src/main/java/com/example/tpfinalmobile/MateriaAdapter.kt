package com.example.tpfinalmobile

import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class MateriaAdapter(
    private val materias: List<Materia>,
    private val context: Context,
    private val usuario: String
) : RecyclerView.Adapter<MateriaAdapter.MateriaViewHolder>() {

    private val coloresFondo = listOf(
        R.color.colorMateria1,
        R.color.colorMateria2,
        R.color.colorMateria3,
        R.color.colorMateria4
    )

    class MateriaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNombreMateria: TextView = itemView.findViewById(R.id.tvNombreMateria)
        val ivObligatoria: ImageView = itemView.findViewById(R.id.ivObligatoria)
        val layoutBoton: View = itemView.findViewById(R.id.layoutBotonMateria)  // 👈 asegurate que lo tenga en item_materia_simple.xml
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MateriaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_materia, parent, false)
        return MateriaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MateriaViewHolder, position: Int) {
        val materia = materias[position]

        holder.tvNombreMateria.text = materia.nombre
        holder.ivObligatoria.visibility = if (materia.esObligatoria) View.VISIBLE else View.GONE
        holder.ivObligatoria.setColorFilter(
            ContextCompat.getColor(context, android.R.color.white),
            PorterDuff.Mode.SRC_IN
        )
        val colorRes = coloresFondo[position % coloresFondo.size]
        val color = ContextCompat.getColor(context, colorRes)
        holder.layoutBoton.setBackgroundColor(color)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetalleMateriaActivity::class.java).apply {
                putExtra("materia", materia)
                putExtra("usuario", usuario)
            }
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = materias.size
}



