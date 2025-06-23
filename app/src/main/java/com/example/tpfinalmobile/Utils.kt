package com.example.tpfinalmobile

import android.view.View
import android.widget.TextView
import com.google.android.material.navigation.NavigationView

fun actualizarNavHeader(navigationView: NavigationView, usuario: String) {
    val headerView: View = navigationView.getHeaderView(0)

    val headerName = headerView.findViewById<TextView>(R.id.headerName)
    val headerEmail = headerView.findViewById<TextView>(R.id.headerEmail)

    headerName.text = "¡Bienvenide, $usuario!"
    headerEmail.text = "$usuario@email.com"
}
