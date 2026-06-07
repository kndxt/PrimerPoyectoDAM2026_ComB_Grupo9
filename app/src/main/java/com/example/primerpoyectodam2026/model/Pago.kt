package com.example.primerpoyectodam2026.model

data class Pago(
    val id: Int = 0,
    val personaId: Int,
    val fechaPago: String,
    val monto: Double,
    val metodoPago: String,
    val tipoPago: String,
    val actividadId: Int?,
    val suscripcionId: Int?
)
