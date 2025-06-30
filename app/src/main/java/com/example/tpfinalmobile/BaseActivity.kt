package com.example.tpfinalmobile

import android.content.Intent
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

abstract class BaseActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    protected lateinit var drawerLayout: DrawerLayout

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val usuario = intent.getStringExtra("usuario") ?: "Usuario"

        when (item.itemId) {
            R.id.nav_home -> startActivity(Intent(this, HomeActivity::class.java).putExtra("usuario", usuario))
            R.id.nav_perfil -> startActivity(Intent(this, PerfilActivity::class.java).putExtra("usuario", usuario))
            R.id.nav_materias -> startActivity(Intent(this, MateriasActivity::class.java).putExtra("usuario", usuario))
            R.id.nav_vencimientos -> startActivity(Intent(this, VencimientosActivity::class.java).putExtra("usuario", usuario))
            R.id.nav_acerca -> startActivity(Intent(this, AcercaActivity::class.java))
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
