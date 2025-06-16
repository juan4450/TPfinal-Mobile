package com.example.tpfinal_mobile

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetalleMateriaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_materia)

        val nombre = intent.getStringExtra("nombre") ?: ""
        val profesor = intent.getStringExtra("profesor") ?: ""
        val horarios = intent.getStringExtra("horarios") ?: ""
        val nota = intent.getIntExtra("nota", 0)
        val presentismo = intent.getIntExtra("presentismo", 0)
        val tp = intent.getStringExtra("tp") ?: ""
        val vencimiento = intent.getStringExtra("vencimiento") ?: ""

        val score = when {
            nota >= 7 -> "Promocionado"
            nota == 6 -> "Zona de promoción"
            nota in 4..5 -> "Regular"
            else -> "Libre"
        }

        findViewById<TextView>(R.id.txtNombreMateria).text = nombre
        findViewById<TextView>(R.id.txtProfesor).text = profesor
        findViewById<TextView>(R.id.txtHorarios).text = horarios
        findViewById<TextView>(R.id.txtNota).text = "Nota: $nota ($score)"
        findViewById<TextView>(R.id.txtPresentismo).text = "Presentismo: $presentismo%"
        findViewById<TextView>(R.id.txtTp).text = tp
        findViewById<TextView>(R.id.txtVencimiento).text = "Vencimiento: $vencimiento"
    }
}
