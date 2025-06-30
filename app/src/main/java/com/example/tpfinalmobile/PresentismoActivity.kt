package com.example.tpfinalmobile

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class PresentismoActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_presentismo)

        // Toolbar + menú
        drawerLayout = ToolbarDrawerHelper.setup(
            this,
            toolbarId = R.id.toolbar,
            drawerLayoutId = R.id.drawer_layout,
            navViewId = R.id.nav_view,
            listener = this
        )

        if (savedInstanceState == null) {
            val materia = intent.getStringExtra("materia") ?: "Sin nombre"
            val presentismo = intent.getIntExtra("presentismo", 0)
            val fragment = PresentismoFragment.newInstance(materia, presentismo)
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        }
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
