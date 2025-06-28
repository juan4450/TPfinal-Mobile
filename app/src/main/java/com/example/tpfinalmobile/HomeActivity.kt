package com.example.tpfinalmobile

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

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

        val usuario = intent.getStringExtra("usuario") ?: "Usuario"

        val userName = findViewById<TextView>(R.id.userName)
        userName.text = getString(R.string.home_welcome, usuario)

        findViewById<Button>(R.id.btnMaterias).setOnClickListener {
            startActivity(Intent(this, MateriasActivity::class.java).putExtra("usuario", usuario))
        }

        findViewById<Button>(R.id.btnCronograma).setOnClickListener {
            startActivity(Intent(this, CronogramaActivity::class.java).putExtra("usuario", usuario))
        }

        findViewById<Button>(R.id.btnVencimientos).setOnClickListener {
            startActivity(Intent(this, VencimientosActivity::class.java).putExtra("usuario", usuario))
        }

        val itemCuatrimestre = findViewById<LinearLayout>(R.id.itemCuatrimestre)
    val itemPromedio = findViewById<LinearLayout>(R.id.itemPromedio)
    val itemAsistencia = findViewById<LinearLayout>(R.id.itemAsistencia)

    fun setupProgressItem(container: LinearLayout, progress: Int, label: String) {
        val indicator = container.findViewById<com.google.android.material.progressindicator.CircularProgressIndicator>(
            R.id.cpiValue
        )
        val tvLabel   = container.findViewById<TextView>(R.id.tvLabel)
        indicator.setProgressCompat(progress, true)
        tvLabel.text = label
    }

    setupProgressItem(itemCuatrimestre,    90, getString(R.string.progress_cuatrimestre, 90))
    setupProgressItem(itemPromedio,    88, getString(R.string.progress_promedio, 88))
    setupProgressItem(itemAsistencia, 75, getString(R.string.progress_asistencia, 75))
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
            }
            R.id.nav_perfil -> {
                startActivity(Intent(this, PerfilActivity::class.java).putExtra("usuario", usuario))
            }

            R.id.nav_materias -> {
                startActivity(Intent(this, MateriasActivity::class.java).putExtra("usuario", usuario))
            }
            R.id.nav_vencimientos -> {
                startActivity(Intent(this, VencimientosActivity::class.java).putExtra("usuario", usuario))
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
