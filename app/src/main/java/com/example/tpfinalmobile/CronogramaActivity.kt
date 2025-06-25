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

class CronogramaActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cronograma)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // Drawer
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

        val cronograma = listOf(
            CronogramaItem("Lunes", "18:00 a 22:00 hs", "Desarrollo de Aplicaciones para Dispositivos Móviles"),
            CronogramaItem("Martes", "18:00 a 22:00 hs", "Metodología de Prueba de Sistemas"),
            CronogramaItem("Miércoles", "18:00 a 22:00 hs", "Desarrollo de Sistemas de Información..."),
            CronogramaItem("Jueves", "18:00 a 22:00 hs", "Tecnologías de la Información y Comunicación")
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerCronograma)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CronogramaAdapter(cronograma)
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
            R.id.nav_cronograma -> { /* ya estás aquí */ }
            R.id.nav_vencimientos -> startActivity(Intent(this, VencimientosActivity::class.java).putExtra("usuario", usuario))
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}


