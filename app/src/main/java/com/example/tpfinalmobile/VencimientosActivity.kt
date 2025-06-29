package com.example.tpfinalmobile

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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView

class VencimientosActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar
    private lateinit var recyclerVencimientos: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vencimientos)
        val usuario = intent.getStringExtra("usuario") ?: "Usuario"

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

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
        toggle.drawerArrowDrawable.color = ContextCompat.getColor(this, android.R.color.darker_gray)

        val btnVolver: ImageView = findViewById(R.id.btnVolver)
        btnVolver.setOnClickListener {
            finish()
        }

        val titulo: TextView = findViewById(R.id.tvTitulo)
        titulo.text = "Próximos vencimientos"

        val materias = listOf(
            Materia(
                nombre = "App. para dispositivos Móviles",
                vencimiento = "01/07",
                tp = "TP Integrador Final",
                nota = 8,
                presentismo = 80,
                profesor = "Prof. Alejandro Peña",
                horarios = "Martes 18hs a 22hs - Miércoles 18hs a 22hs",
                apertura = "Martes 3 de junio de 2025, 00:00hs"
            ),
            Materia(
                nombre = "Metodología de Prueba de Sistemas",
                vencimiento = "03/07",
                tp = "TP Final",
                nota = 6,
                presentismo = 75,
                profesor = "Prof. Martínez",
                horarios = "Lunes 18hs a 22hs - Miércoles 18hs a 22hs",
                apertura = "Lunes 2 de junio de 2025, 00:00hs"
            ),
            Materia(
                nombre = "Práctica Profesional II",
                vencimiento = "05/07",
                tp = "TP Entregable",
                nota = 7,
                presentismo = 88,
                profesor = "Prof. Fernández",
                horarios = "Martes 18hs a 22hs - Jueves 18hs a 22hs",
                apertura = "Martes 10 de junio de 2025, 00:00hs"
            ),
            Materia(
                nombre = "Tecnologías de la Información",
                vencimiento = "10/07",
                tp = "TP Parcial",
                nota = 5,
                presentismo = 92,
                profesor = "Prof. López",
                horarios = "Miércoles 18hs a 22hs - Viernes 18hs a 22hs",
                apertura = "Miércoles 5 de junio de 2025, 00:00hs"
            )
        )


        recyclerVencimientos = findViewById(R.id.recyclerVencimientos)
        recyclerVencimientos.layoutManager = LinearLayoutManager(this)
        recyclerVencimientos.adapter = VencimientosAdapter(materias) { materiaSeleccionada ->
            val intent = Intent(this, DetalleVencimientoActivity::class.java)
            intent.putExtra("materia", materiaSeleccionada)
            intent.putExtra("usuario", usuario)
            startActivity(intent)
        }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val usuario = intent.getStringExtra("usuario") ?: "Usuario"

        when (item.itemId) {
            R.id.nav_perfil -> startActivity(
                Intent(
                    this,
                    PerfilActivity::class.java
                ).putExtra("usuario", usuario)
            )

            R.id.nav_home -> startActivity(
                Intent(
                    this,
                    HomeActivity::class.java
                ).putExtra("usuario", usuario)
            )

            R.id.nav_materias -> startActivity(
                Intent(
                    this,
                    MateriasActivity::class.java
                ).putExtra("usuario", usuario)
            )

            R.id.nav_vencimientos -> { /* Ya estás aquí */
            }

            R.id.nav_acerca -> startActivity(Intent(this, AcercaActivity::class.java))
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
