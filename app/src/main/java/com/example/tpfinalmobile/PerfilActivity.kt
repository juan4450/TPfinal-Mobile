package com.example.tpfinalmobile

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.tpfinalmobile.CronogramaActivity
import com.example.tpfinalmobile.R
import com.example.tpfinalmobile.VencimientosActivity
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

        // Obtener nombre de usuario del intent
        val usuario = intent.getStringExtra("usuario") ?: "Usuario"

        // Mostrar el nombre en el perfil
        val nombreText = findViewById<TextView>(R.id.tvNombre)
        nombreText.text = usuario
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
                // Estás en esta pantalla
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
