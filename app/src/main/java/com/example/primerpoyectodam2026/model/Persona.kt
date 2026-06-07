package com.example.primerpoyectodam2026.model

data class Persona(
    val id: Int = 0,
    val nombre: String,
    val apellido: String,
    val dni: String,
    val telefono: String,
    val email: String,
    val aptoFisico: Boolean,
    val activo: Boolean,
    val tipoPersona: String,
    val fechaInscripcion: String?
)