package com.example.tpfinalmobile

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager

class CalificacionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_presentismo)


        // Datos mockeados
        val listaPruebas = listOf(
            CalificacionItem("Primer Parcial", "90/100"),
            CalificacionItem("Segundo Parcial", "75/100")
        )

        val listaTPs = listOf(
            CalificacionItem("TP 1", "80/100"),
            CalificacionItem("TP Final", "100/100")
        )

        // Pruebas
        val recyclerPruebas = findViewById<RecyclerView>(R.id.recyclerViewPruebas)
        recyclerPruebas.layoutManager = LinearLayoutManager(this)
        recyclerPruebas.adapter = CalificacionAdapter(listaPruebas)

        // TPs
        val recyclerTPs = findViewById<RecyclerView>(R.id.recyclerViewTPs)
        recyclerTPs.layoutManager = LinearLayoutManager(this)
        recyclerTPs.adapter = CalificacionAdapter(listaTPs)


    }
}