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

class MateriasActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_materias)

        // Toolbar + menú
        drawerLayout = ToolbarDrawerHelper.setup(
            this,
            toolbarId = R.id.toolbar,
            drawerLayoutId = R.id.drawer_layout,
            navViewId = R.id.nav_view,
            listener = this
        )

        val usuario = intent.getStringExtra("usuario") ?: "Usuario"

        val materias = listOf(
            Materia(
                nombre = "App. para dispositivos Móviles",
                vencimiento = "1 de julio de 2025",
                tp = "TP Integrador Final",
                nota = 8,
                presentismo = 75,
                apertura = "15 de mayo de 2025",
                profesor = "Prof. Alejandro Peña",
                horarios = "Martes 18hs a 22hs - Miércoles 18hs a 22hs",
                esObligatoria = true
            ),
            Materia(
                nombre = "Metodología de Prueba de Sistemas",
                vencimiento = "3 de julio de 2025",
                tp = "TP Final",
                nota = 6,
                presentismo = 80,
                apertura = "11 de mayo de 2025",
                profesor = "Prof. Martínez",
                horarios = "Lunes 18hs a 22hs - Miércoles 18hs a 22hs",
                esObligatoria = false
            ),
            Materia(
                nombre = "Práctica Profesional II",
                vencimiento = "5 de julio de 2025",
                tp = "TP Entregable",
                nota = 7,
                presentismo = 65,
                apertura = "3 de junio de 2025",
                profesor = "Prof. Fernández",
                horarios = "Martes 18hs a 22hs - Jueves 18hs a 22hs",
                esObligatoria = false
            ),
            Materia(
                nombre = "Tecnologías de la Información",
                vencimiento = "10 de julio de 2025",
                tp = "TP Parcial",
                nota = 5,
                presentismo = 75,
                apertura = "7 de mayo de 2025",
                profesor = "Prof. López",
                horarios = "Miércoles 18hs a 22hs - Viernes 18hs a 22hs",
                esObligatoria = false
            )
        )


        val btnVolver: ImageView = findViewById(R.id.btnVolver)
        btnVolver.setOnClickListener {
            finish()
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
}
