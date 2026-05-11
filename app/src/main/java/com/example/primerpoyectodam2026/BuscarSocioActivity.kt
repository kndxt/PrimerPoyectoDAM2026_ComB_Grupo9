package com.example.primerpoyectodam2026

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Button

class BuscarSocioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_socio)

        val btnBuscarSocio = findViewById<Button>(R.id.btnBuscarSocio)
        btnBuscarSocio.setOnClickListener {
            val intent = Intent(this, SuscripcionActivity::class.java)
            startActivity(intent)
        }
    }
}