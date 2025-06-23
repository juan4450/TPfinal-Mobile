package com.example.tpfinalmobile

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView

class VencimientosActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vencimientos)

        // Toolbar
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Drawer y menú
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

        // Usuario desde Login
        val usuario = intent.getStringExtra("usuario") ?: "Usuario"
        actualizarNavHeader(navigationView, usuario)

        // Lista mock de vencimientos
        val vencimientos = listOf(
            Materia(
                nombre = "Desarrollo de Aplicaciones para Dispositivos Móviles",
                vencimiento = "Lunes 24/06",
                tp = "TP Entregable N°1",
                nota = 9,
                presentismo = 95,
                profesor = "Prof. Alejandro Peña",
                horarios = "Martes 18hs - Miércoles 18hs"
            ),
            Materia(
                nombre = "Metodología de Prueba de Sistemas",
                vencimiento = "Miércoles 26/06",
                tp = "Informe Intermedio",
                nota = 7,
                presentismo = 88,
                profesor = "Prof. Martínez",
                horarios = "Lunes 18hs - Miércoles 18hs"
            ),
            Materia(
                nombre = "Tecnologías de la Información y Comunicación",
                vencimiento = "Viernes 28/06",
                tp = "TP Integrador Final",
                nota = 8,
                presentismo = 90,
                profesor = "Prof. García",
                horarios = "Jueves 18hs - Viernes 18hs"
            )
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerVencimientos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = MateriaAdapter(vencimientos, this)
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
            R.id.nav_vencimientos -> { /* Ya estás aquí */ }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
