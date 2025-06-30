package com.example.tpfinalmobile
import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GravityCompat
import com.google.android.material.button.MaterialButton

class PerfilActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Toolbar
        drawerLayout = ToolbarDrawerHelper.setup(
            this,
            toolbarId = R.id.toolbar,
            drawerLayoutId = R.id.drawer_layout,
            navViewId = R.id.nav_view,
            listener = this // usa onNavigationItemSelected de BaseActivity
        )

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

        val btnVolver: ImageView = findViewById(R.id.btnVolver)
        btnVolver.setOnClickListener {
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

}
