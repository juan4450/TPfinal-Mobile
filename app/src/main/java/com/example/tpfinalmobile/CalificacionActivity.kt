package com.example.tpfinalmobile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView

class CalificacionActivity : BaseActivity() {

    private lateinit var btnVolver: ImageView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calificacion)

        // Toolbar + menú
        drawerLayout = ToolbarDrawerHelper.setup(
            this,
            toolbarId = R.id.toolbar,
            drawerLayoutId = R.id.drawer_layout,
            navViewId = R.id.nav_view,
            listener = this
        )


        // Botón volver
        btnVolver = findViewById(R.id.btnVolver)
        btnVolver.setOnClickListener {
            finish()
        }

        // Obtener materia
        val materia = intent.getSerializableExtra("materia") as? Materia

        if (materia == null) {
            Toast.makeText(this, "No se pudo cargar la materia", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Mostrar info de la materia
        findViewById<TextView>(R.id.txtTitulo).text = materia.nombre

        // Condición promocional y promedio
        val promedio = calcularPromedioMock(materia)
        val condicion = obtenerCondicion(promedio)
        findViewById<TextView>(R.id.tvCondicion).text = condicion
        findViewById<TextView>( R.id.tvPromedio).text = "Promedio ${promedio}/10"

        // Simular notas
        val listaParciales = listOf(
            CalificacionItem("Primer Parcial", notaParcial1(materia.nombre)),
            CalificacionItem("Segundo Parcial", notaParcial2(materia.nombre))
        )
        val listaTPs = listOf(
            CalificacionItem("TP 1", notaTp1(materia.nombre)),
            CalificacionItem("TP Final", notaTpFinal(materia.nombre))
        )

        val recyclerPruebas = findViewById<RecyclerView>(R.id.recyclerViewPruebas)
        recyclerPruebas.layoutManager = LinearLayoutManager(this)
        recyclerPruebas.adapter = CalificacionAdapter(listaParciales)

        val recyclerTPs = findViewById<RecyclerView>(R.id.recyclerViewTPs)
        recyclerTPs.layoutManager = LinearLayoutManager(this)
        recyclerTPs.adapter = CalificacionAdapter(listaTPs)
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    private fun calcularPromedioMock(m: Materia): Double {
        val n1 = notaParcial1(m.nombre).toDouble()
        val n2 = notaParcial2(m.nombre).toDouble()
        val n3 = notaTp1(m.nombre).toDouble()
        val n4 = notaTpFinal(m.nombre).toDouble()
        return ((n1 + n2 + n3 + n4) / 4 * 10.0).toInt() / 10.0
    }

    private fun obtenerCondicion(prom: Double): String {
        return when {
            prom >= 7 -> "Condición de promoción"
            prom in 4.0..6.9 -> "Regular"
            else -> "Libre"
        }
    }

    // Simulación de notas por materia (puede variar)
    private fun notaParcial1(nombre: String): String = when (nombre) {
        "App. para dispositivos Móviles" -> "9"
        "Metodología de Prueba de Sistemas" -> "6"
        "Práctica Profesional II" -> "8"
        "Tecnologías de la Información" -> "5"
        else -> "0"
    }

    private fun notaParcial2(nombre: String): String = when (nombre) {
        "App. para dispositivos Móviles" -> "7.5"
        "Metodología de Prueba de Sistemas" -> "6"
        "Práctica Profesional II" -> "7"
        "Tecnologías de la Información" -> "5"
        else -> "0"
    }

    private fun notaTp1(nombre: String): String = when (nombre) {
        "App. para dispositivos Móviles" -> "8"
        "Metodología de Prueba de Sistemas" -> "6.5"
        "Práctica Profesional II" -> "7.5"
        "Tecnologías de la Información" -> "4"
        else -> "0"
    }

    private fun notaTpFinal(nombre: String): String = when (nombre) {
        "App. para dispositivos Móviles" -> "10"
        "Metodología de Prueba de Sistemas" -> "7"
        "Práctica Profesional II" -> "8"
        "Tecnologías de la Información" -> "5"
        else -> "0"
    }

}
