package com.example.primerpoyectodam2026

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.primerpoyectodam2026.dao.SuscripcionDao

class CredencialActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_credencial)

        val nombre =
            intent.getStringExtra("PERSONA_NOMBRE") ?: ""

        val apellido =
            intent.getStringExtra("PERSONA_APELLIDO") ?: ""

        val email =
            intent.getStringExtra("PERSONA_EMAIL") ?: ""

        val telefono =
            intent.getStringExtra("PERSONA_TELEFONO") ?: ""

        val idSocio =
            intent.getIntExtra("PERSONA_ID", 0)

        val tvNombre =
            findViewById<TextView>(R.id.tvNombre)

        val tvContacto =
            findViewById<TextView>(R.id.tvContacto)

        val tvId =
            findViewById<TextView>(R.id.tvId)

        val tvEmail =
            findViewById<TextView>(R.id.tvEmail)

        val tvEstadoCuota =
            findViewById<TextView>(R.id.tvEstadoCuota)

        tvNombre.text =
            "$nombre $apellido"

        tvContacto.text =
            telefono

        tvId.text =
            idSocio.toString()

        tvEmail.text =
            email

        val suscripcionDao =
            SuscripcionDao(this)

        tvEstadoCuota.text =
            suscripcionDao.obtenerEstadoCuota(idSocio)

        val btnIrMenu =
            findViewById<Button>(R.id.btnIrMenu)

        btnIrMenu.setOnClickListener {
            finish()
        }
    }
}