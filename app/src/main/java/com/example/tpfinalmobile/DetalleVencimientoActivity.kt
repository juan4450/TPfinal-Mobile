package com.example.tpfinalmobile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.core.content.ContextCompat

class DetalleVencimientoActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar
    private lateinit var btnVolver: ImageView
    private lateinit var btnDescargar: ImageView
    private lateinit var tvEstadoEntrega: TextView
    private lateinit var btnMarcarCompletado: Button

    private var estadoEntregado = false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_vencimiento)

        // Toolbar y Drawer
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        toggle.drawerArrowDrawable.color = getColor(R.color.menu_icon_gray)

        btnVolver = findViewById(R.id.btnVolver)
        btnVolver.setOnClickListener {
            finish()
        }

        val materia = intent.getSerializableExtra("materia") as? Materia

        materia?.let {
            findViewById<TextView>(R.id.tvTituloMateria).text = it.nombre
            findViewById<TextView>(R.id.tvTpDetalle).text = it.tp
            findViewById<TextView>(R.id.tvApertura).text = "Apertura: ${aperturaMock(it.nombre)}"
            findViewById<TextView>(R.id.tvCierre).text = "Cierre: ${it.vencimiento}"
            tvEstadoEntrega = findViewById(R.id.tvEstadoEntrega)
            tvEstadoEntrega.text = "Estado: Pendiente"
        }

        btnDescargar = findViewById(R.id.ivDescargar)
        btnDescargar.setOnClickListener {
            Toast.makeText(this, "Descargando TP simulado...", Toast.LENGTH_SHORT).show()
        }

        btnMarcarCompletado = findViewById(R.id.btnMarcarCompletado)
        tvEstadoEntrega = findViewById(R.id.tvEstadoEntrega)

        val colorResId = when (materia?.nombre) {
            "App. para dispositivos Móviles" -> R.color.colorMateria1
            "Metodología de Prueba de Sistemas" -> R.color.colorMateria2
            "Práctica Profesional II" -> R.color.colorMateria3
            "Tecnologías de la Información" -> R.color.colorMateria4
            else -> R.color.colorPrimary
        }

        btnMarcarCompletado.setBackgroundColor(ContextCompat.getColor(this, colorResId))

        btnMarcarCompletado.setOnClickListener {
            estadoEntregado = !estadoEntregado
            tvEstadoEntrega.text = if (estadoEntregado) "Estado: Completado" else "Estado: Pendiente"
        }

    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val usuario = intent.getStringExtra("usuario") ?: "Usuario"
        when (item.itemId) {
            R.id.nav_perfil -> startActivity(Intent(this, PerfilActivity::class.java).putExtra("usuario", usuario))
            R.id.nav_home -> startActivity(Intent(this, HomeActivity::class.java).putExtra("usuario", usuario))
            R.id.nav_materias -> startActivity(Intent(this, MateriasActivity::class.java).putExtra("usuario", usuario))
            R.id.nav_vencimientos -> startActivity(Intent(this, VencimientosActivity::class.java).putExtra("usuario", usuario))
            R.id.nav_acerca -> startActivity(Intent(this, AcercaActivity::class.java))
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun aperturaMock(nombre: String): String {
        return when (nombre) {
            "App. para dispositivos Móviles" -> "15 de junio de 2025"
            "Metodología de Prueba de Sistemas" -> "17 de junio de 2025"
            "Práctica Profesional II" -> "20 de junio de 2025"
            "Tecnologías de la Información" -> "22 de junio de 2025"
            else -> "Fecha no disponible"
        }
    }

}
