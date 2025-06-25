package com.example.tpfinalmobile

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView

class MateriasActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materias)

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

        val materias = listOf(
            Materia(
                nombre = "Desarrollo de Aplicaciones para Dispositivos Móviles",
                vencimiento = "1 de julio de 2025",
                tp = "TP Integrador Final",
                nota = 8,
                presentismo = 80,
                apertura = "15 de mayo de 2025",
                profesor = "Prof. Alejandro Peña",
                horarios = "Martes 18hs a 22hs - Miércoles 18hs a 22hs"
            ),
            Materia(
                nombre = "Metodología de Prueba de Sistemas",
                vencimiento = "3 de julio de 2025",
                tp = "TP Final",
                nota = 6,
                apertura = "11 de mayo de 2025",
                presentismo = 75,
                profesor = "Prof. Martínez",
                horarios = "Lunes 18hs a 22hs - Miércoles 18hs a 22hs"
            ),
            Materia(
                nombre = "Desarrollo de Sistemas de Información Orientados a la Gestión y Apoyo a las Decisiones",
                vencimiento = "5 de julio de 2025",
                tp = "TP Entregable",
                apertura = "3 de junio de 2025",
                nota = 7,
                presentismo = 88,
                profesor = "Prof. Fernández",
                horarios = "Martes 18hs a 22hs - Jueves 18hs a 22hs"
            ),
            Materia(
                nombre = "Tecnologías de la Información y Comunicación",
                vencimiento = "10 de julio de 2025",
                tp = "TP Parcial",
                apertura = "7 de mayo de 2025",
                nota = 5,
                presentismo = 92,
                profesor = "Prof. López",
                horarios = "Miércoles 18hs a 22hs - Viernes 18hs a 22hs"
            )
        )

        findViewById<ImageView>(R.id.btnVolver).setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerMaterias)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MateriaAdapter(materias, this, usuario)
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
            R.id.nav_materias -> { /* Ya estás en esta pantalla */ }
            R.id.nav_cronograma -> startActivity(Intent(this, CronogramaActivity::class.java).putExtra("usuario", usuario))
            R.id.nav_vencimientos -> startActivity(Intent(this, VencimientosActivity::class.java).putExtra("usuario", usuario))
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
