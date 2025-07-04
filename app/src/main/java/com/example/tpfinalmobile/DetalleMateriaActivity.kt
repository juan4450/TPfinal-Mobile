package com.example.tpfinalmobile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.card.MaterialCardView
import com.google.android.material.navigation.NavigationView

class DetalleMateriaActivity : BaseActivity() {

    private lateinit var btnVolver: ImageView
    private lateinit var btnVerVencimientos: TextView
    private lateinit var cardCalificaciones: MaterialCardView
    private lateinit var cardPresentismo: MaterialCardView


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_materia)

        // Toolbar + menú
        drawerLayout = ToolbarDrawerHelper.setup(
            this,
            toolbarId = R.id.toolbar,
            drawerLayoutId = R.id.drawer_layout,
            navViewId = R.id.nav_view,
            listener = this
        )

        val usuario = intent.getStringExtra("usuario") ?: "Usuario"

        btnVolver = findViewById(R.id.btnVolver)
        btnVolver.setOnClickListener {
            finish()
        }

        btnVerVencimientos = findViewById(R.id.btnVerVencimientos)

        val materia = intent.getSerializableExtra("materia") as? Materia

        materia?.let {
            findViewById<TextView>(R.id.tvNombreMateria).text = it.nombre
            findViewById<TextView>(R.id.tvProfesor).text = it.profesor
            findViewById<TextView>(R.id.tvHorarios).text = it.horarios
            findViewById<TextView>(R.id.tvTpFecha).text = it.vencimiento
            findViewById<TextView>(R.id.tvTpDescripcion).text = it.tp
            findViewById<TextView>(R.id.tvNota).text = "Nota: ${it.nota} (${estadoNota(it.nota)})"
            findViewById<TextView>(R.id.tvPresentismo).text = "Presentismo: ${it.presentismo}%"

            btnVerVencimientos.setOnClickListener {
                val intent = Intent(this, DetalleVencimientoActivity::class.java)
                intent.putExtra("materia", materia)
                intent.putExtra("usuario", usuario)
                startActivity(intent)
            }

            val colorResId = when (it.nombre) {
            "App. para dispositivos Móviles" -> R.color.colorMateria1
            "Metodología de Prueba de Sistemas" -> R.color.colorMateria2
            "Práctica Profesional II" -> R.color.colorMateria3
            "Tecnologías de la Información" -> R.color.colorMateria4
            else -> R.color.colorPrimary
        }
            val color = ContextCompat.getColor(this, colorResId)

            findViewById<android.view.View>(R.id.headerVencimiento).setBackgroundColor(color)
            findViewById<android.view.View>(R.id.headerCalificaciones).setBackgroundColor(color)
            findViewById<android.view.View>(R.id.headerPresentismo).setBackgroundColor(color)
        }

        cardCalificaciones = findViewById(R.id.cardCalificaciones)
        cardCalificaciones.setOnClickListener {
            val intent = Intent(this, CalificacionActivity::class.java)
            intent.putExtra("usuario", usuario)
            intent.putExtra("materia", materia)
            startActivity(intent)
        }

        cardPresentismo = findViewById(R.id.cardPresentismo)
        cardPresentismo.setOnClickListener {
            materia?.let {
            val intent = Intent(this, PresentismoActivity::class.java)
            intent.putExtra("usuario", usuario)
            intent.putExtra("materia", it.nombre)
            intent.putExtra("presentismo", it.presentismo)
            startActivity(intent)
            }
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }


    private fun estadoNota(nota: Int): String {
        return when {
            nota >= 7 -> "Promocionado"
            nota == 6 -> "Zona de Promoción"
            nota in 4..5 -> "Regular"
            else -> "Libre"
        }
    }
}

