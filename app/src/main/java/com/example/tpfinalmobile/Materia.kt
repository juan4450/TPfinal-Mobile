package com.example.tpfinalmobile

import java.io.Serializable

data class Materia(
    val nombre: String,
    val vencimiento: String,
    val tp: String,
    val nota: Int,
    val presentismo: Int,
    val profesor: String,
    val horarios: String,
    val apertura: String,
    val esObligatoria: Boolean = false
) : Serializable


