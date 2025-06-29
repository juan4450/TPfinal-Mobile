package com.example.tpfinalmobile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.button.MaterialButton
import com.google.android.material.navigation.NavigationView

class PerfilActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar

    @SuppressLint("SetTextI18n", "MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Toolbar
        toolbar = findViewById(R.id.toolbar)
        toolbar.title = getString(R.string.app_name)
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

        // Usuario recibido
        val usuario = intent.getStringExtra("usuario") ?: "Juan"

        findViewById<TextView>(R.id.tvNombre).text = usuario
        findViewById<TextView>(R.id.tvEmail).text = usuario
        findViewById<TextView>(R.id.tvTelefono).text = "12.345.678"

        // Botones
        findViewById<MaterialButton>(R.id.btnCambiarContrasena).setOnClickListener {
            Toast.makeText(this, "Funcionalidad en desarrollo", Toast.LENGTH_SHORT).show()
        }


        // Datos académicos con texto combinado (como el mock)
        val tvIngreso = findViewById<TextView>(R.id.tvIngreso)
        val tvMaterias = findViewById<TextView>(R.id.tvMaterias)
        val tvPromedio = findViewById<TextView>(R.id.tvPromedio)
        val tvEstado = findViewById<TextView>(R.id.tvEstado)

        tvIngreso.text = getString(R.string.profile_admissionYear_label) + " 2024"
        tvMaterias.text = getString(R.string.profile_subjects_label) + " 13/23"
        tvPromedio.text = getString(R.string.profile_average_label) + " 8.7"
        tvEstado.text = getString(R.string.profile_academicStatus_label) + " Regular"
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
            R.id.nav_home -> {
                startActivity(Intent(this, HomeActivity::class.java).putExtra("usuario", usuario))
            }
            R.id.nav_materias -> {
                startActivity(Intent(this, MateriasActivity::class.java).putExtra("usuario", usuario))
            }
            R.id.nav_vencimientos -> {
                startActivity(Intent(this, VencimientosActivity::class.java).putExtra("usuario", usuario))
            }
            R.id.nav_perfil -> {
                // Ya estás en Perfil
            }
            R.id.nav_acerca -> startActivity(Intent(this, AcercaActivity::class.java))
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
