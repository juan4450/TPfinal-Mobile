// VencimientosActivity.kt
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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tpfinalmobile.HomeActivity
import com.example.tpfinalmobile.MateriasActivity
import com.google.android.material.navigation.NavigationView

class VencimientosActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar
    private lateinit var recyclerVencimientos: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vencimientos)

        // Toolbar personalizada
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // DrawerLayout y NavigationView
        drawerLayout = findViewById(R.id.drawer_layout)
        navigationView = findViewById(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        // Botón volver (ícono a la izquierda del título)
        val btnVolver: ImageView = findViewById(R.id.btnVolver)
        btnVolver.setOnClickListener {
            finish()
        }

        // Título
        val titulo: TextView = findViewById(R.id.tvTitulo)
        titulo.text = "Próximos vencimientos"

        // Lista de materias (mockeadas)
        val materias = listOf(
            Materia(
                nombre = "Desarrollo de Aplicaciones para Dispositivos Móviles",
                vencimiento = "1 de julio de 2025",
                tp = "TP Integrador Final",
                nota = 8,
                presentismo = 80,
                profesor = "Prof. Alejandro Peña",
                horarios = "Martes 18hs a 22hs - Miércoles 18hs a 22hs"
            ),
            Materia(
                nombre = "Metodología de Prueba de Sistemas",
                vencimiento = "3 de julio de 2025",
                tp = "TP Final",
                nota = 6,
                presentismo = 75,
                profesor = "Prof. Martínez",
                horarios = "Lunes 18hs a 22hs - Miércoles 18hs a 22hs"
            ),
            Materia(
                nombre = "Desarrollo de Sistemas de Información Orientados a la Gestión y Apoyo a las Decisiones",
                vencimiento = "5 de julio de 2025",
                tp = "TP Entregable",
                nota = 7,
                presentismo = 88,
                profesor = "Prof. Fernández",
                horarios = "Martes 18hs a 22hs - Jueves 18hs a 22hs"
            ),
            Materia(
                nombre = "Tecnologías de la Información y Comunicación",
                vencimiento = "10 de julio de 2025",
                tp = "TP Parcial",
                nota = 5,
                presentismo = 92,
                profesor = "Prof. López",
                horarios = "Miércoles 18hs a 22hs - Viernes 18hs a 22hs"
            )
        )

        recyclerVencimientos = findViewById(R.id.recyclerVencimientos)
        recyclerVencimientos.layoutManager = LinearLayoutManager(this)
        recyclerVencimientos.adapter = VencimientosAdapter(materias)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Implementa acciones al seleccionar items si agregás más
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
