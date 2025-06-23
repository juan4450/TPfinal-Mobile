package com.example.tpfinalmobile

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class DetalleMateriaActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar
    private lateinit var btnVolver: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_materia)

        // Toolbar y menú lateral
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
        toggle.drawerArrowDrawable.color = getColor(R.color.menu_icon_gray)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        val usuario = intent.getStringExtra("usuario") ?: "Usuario"
        actualizarNavHeader(navigationView, usuario)

        // Botón de volver (flecha junto al título)
        btnVolver = findViewById(R.id.btnVolver)
        btnVolver.setOnClickListener {
            finish()
        }

        // Recibir materia enviada desde MateriasActivity
        val materia = intent.getSerializableExtra("materia") as? Materia

        // Asignar datos si no es null
        materia?.let {
            findViewById<TextView>(R.id.tvNombreMateria).text = it.nombre
            findViewById<TextView>(R.id.tvProfesor).text = it.profesor
            findViewById<TextView>(R.id.tvHorarios).text = it.horarios
            findViewById<TextView>(R.id.tvTpFecha).text = it.vencimiento
            findViewById<TextView>(R.id.tvTpDescripcion).text = it.tp
            findViewById<TextView>(R.id.tvNota).text = "Nota: ${it.nota} (${estadoNota(it.nota)})"
            findViewById<TextView>(R.id.tvPresentismo).text = "Presentismo: ${it.presentismo}%"
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
            R.id.nav_home -> startActivity(Intent(this, HomeActivity::class.java).putExtra("usuario", usuario))
            R.id.nav_materias -> startActivity(Intent(this, MateriasActivity::class.java).putExtra("usuario", usuario))
            R.id.nav_cronograma -> startActivity(Intent(this, CronogramaActivity::class.java).putExtra("usuario", usuario))
            R.id.nav_vencimientos -> startActivity(Intent(this, VencimientosActivity::class.java).putExtra("usuario", usuario))
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
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

