package com.example.primerpoyectodam2026

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SuscripcionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suscripcion)

        val btnSuscripcion = findViewById<Button>(R.id.btnSuscripcion)
        btnSuscripcion.setOnClickListener {
            val intent = Intent(this, PagoCuotaActivity::class.java)
            startActivity(intent)
        }

    }
}