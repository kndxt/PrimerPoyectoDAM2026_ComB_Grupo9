package com.example.primerpoyectodam2026

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.primerpoyectodam2026.dao.PersonaDao

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etEmail =
            findViewById<EditText>(R.id.etEmail)

        val btnIrMenu =
            findViewById<Button>(R.id.btnIrMenu)

        val personaDao =
            PersonaDao(this)

        btnIrMenu.setOnClickListener {

            val email =
                etEmail.text.toString().trim()

            if (email.isEmpty()) {

                Toast.makeText(
                    this,
                    "Ingrese un email",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val persona =
                personaDao.buscarPorEmail(email)

            if (persona == null) {

                Toast.makeText(
                    this,
                    "Usuario no encontrado",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            if (persona.tipoPersona == "NO_SOCIO") {

                Toast.makeText(
                    this,
                    "Credenciales inválidas",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val intent = Intent(
                this,
                MenuActivity::class.java
            )

            intent.putExtra(
                "PERSONA_ID",
                persona.id
            )

            intent.putExtra(
                "PERSONA_NOMBRE",
                persona.nombre
            )

            intent.putExtra(
                "PERSONA_APELLIDO",
                persona.apellido
            )

            intent.putExtra(
                "TIPO_PERSONA",
                persona.tipoPersona
            )

            intent.putExtra(
                "PERSONA_EMAIL",
                persona.email
            )

            intent.putExtra(
                "PERSONA_TELEFONO",
                persona.telefono
            )

            startActivity(intent)
        }
    }
}