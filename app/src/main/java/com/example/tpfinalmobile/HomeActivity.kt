package com.example.tpfinalmobile

import HomeAdapter
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView

class HomeActivity : BaseActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Toolbar + menú
        drawerLayout = ToolbarDrawerHelper.setup(
            this,
            toolbarId = R.id.toolbar,
            drawerLayoutId = R.id.drawer_layout,
            navViewId = R.id.nav_view,
            listener = this
        )

        val usuario = intent.getStringExtra("usuario") ?: "Usuario"

        val userName = findViewById<TextView>(R.id.userName)
        userName.text = getString(R.string.home_welcome, usuario)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerHome)
        val recyclerViewScore: RecyclerView = findViewById(R.id.recyclerScore)
        val recyclerViewCronograma: RecyclerView = findViewById(R.id.recyclerCronograma)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerViewScore.layoutManager = LinearLayoutManager(this)
        recyclerViewCronograma.layoutManager = LinearLayoutManager(this)


        val homeItems = listOf(
            HomeItem("75% App. para dispositivos Móviles", R.drawable.ic_check_green, R.color.home_items_text, R.drawable.bg_classic_item),
            HomeItem("80% Metodología Prueba de Sistemas", R.drawable.ic_check_green, R.color.home_items_text,  R.drawable.bg_classic_item),
            HomeItem("65% Tecnologías de la Información", R.drawable.ic_error, R.color.error, R.drawable.bg_error_rounded),
            HomeItem("75% Práctica Profesional II", R.drawable.ic_check_green, R.color.home_items_text,  R.drawable.bg_classic_item),
        )

        val scoreItems = listOf(
            HomeItem("8.5 App. para dispositivos Móviles", R.drawable.ic_check_green, R.color.home_items_text, R.drawable.bg_classic_item),
            HomeItem("9 Metodología Prueba de Sistemas", R.drawable.ic_check_green, R.color.home_items_text,  R.drawable.bg_classic_item),
            HomeItem("5.5 Tecnologías de la Información",  R.drawable.ic_error, R.color.error, R.drawable.bg_error_rounded),
            HomeItem("10 Práctica Profesional II", R.drawable.ic_check_green, R.color.home_items_text,  R.drawable.bg_classic_item),
        )

        val cronogramaItems = listOf(
            CronogramaItem("Lunes 18:00-22:00", "Práctica Profesional II", "Presencial"),
            CronogramaItem("Martes 18:00-22:00", "App. para dispositivos Móviles", "Virtual"),
            CronogramaItem("Jueves 18:00-22:00", "Tecnologías de la Información", "Presencial"),
            CronogramaItem("Viernes 18:00-22:00", "Metodología Prueba de Sistemas", "Virtual"),
        )

        val adapter = HomeAdapter(homeItems)
        recyclerView.adapter = adapter

        val scoreAdapter = HomeAdapter(scoreItems)
        recyclerViewScore.adapter = scoreAdapter

        val cronogramaAdapter = CronogramaAdapter(cronogramaItems)
        recyclerViewCronograma.adapter = cronogramaAdapter

    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
