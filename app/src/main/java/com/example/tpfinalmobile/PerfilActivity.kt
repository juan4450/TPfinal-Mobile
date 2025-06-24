package com.example.tpfinalmobile

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

        // Obtener datos del intent
        val usuario = intent.getStringExtra("usuario") ?: "Juan"

        // Mostrar en los campos
        findViewById<TextView>(R.id.tvNombre).text = usuario
        findViewById<TextView>(R.id.tvEmail).text = usuario
        findViewById<TextView>(R.id.tvTelefono).text = "12.345.678" // Número fijo simulado

        // Botón CAMBIAR CONTRASEÑA
        findViewById<MaterialButton>(R.id.btnCambiarContrasena).setOnClickListener {
            Toast.makeText(this, "Funcionalidad en desarrollo", Toast.LENGTH_SHORT).show()
        }

        // Botón CERRAR SESIÓN
        findViewById<MaterialButton>(R.id.btnCerrarSesion).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
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
            R.id.nav_home -> {
                startActivity(Intent(this, HomeActivity::class.java).putExtra("usuario", usuario))
            }
            R.id.nav_materias -> {
                startActivity(Intent(this, MateriasActivity::class.java).putExtra("usuario", usuario))
            }
            R.id.nav_cronograma -> {
                startActivity(Intent(this, CronogramaActivity::class.java).putExtra("usuario", usuario))
            }
            R.id.nav_vencimientos -> {
                startActivity(Intent(this, VencimientosActivity::class.java).putExtra("usuario", usuario))
            }
            R.id.nav_perfil -> {
                // Ya estás en Perfil
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
