package com.example.primerpoyectodam2026.model

data class Suscripcion(
    val id: Int = 0,
    val personaId: Int,
    val tipoPlan: String,
    val fechaInicio: String,
    val fechaVencimiento: String
)