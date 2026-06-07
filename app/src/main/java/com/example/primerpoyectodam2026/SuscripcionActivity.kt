package com.example.primerpoyectodam2026

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SuscripcionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suscripcion)

        val personaId = intent.getIntExtra("PERSONA_ID", 0)
        val nombre = intent.getStringExtra("PERSONA_NOMBRE")
        val apellido = intent.getStringExtra("PERSONA_APELLIDO")
        val tipoPersona = intent.getStringExtra("TIPO_PERSONA")

        val rb6Cuotas =
            findViewById<RadioButton>(R.id.rb6Cuotas)

        val rb3Cuotas =
            findViewById<RadioButton>(R.id.rb3Cuotas)

        val rbContado =
            findViewById<RadioButton>(R.id.rbContado)

        val radios = listOf(
            rb6Cuotas,
            rb3Cuotas,
            rbContado
        )

        radios.forEach { radio ->

            radio.setOnClickListener {

                radios.forEach {
                    it.isChecked = false
                }

                radio.isChecked = true
            }
        }

        val btnSuscripcion =
            findViewById<Button>(R.id.btnSuscripcion)

        btnSuscripcion.setOnClickListener {

            val radioSeleccionado =
                radios.find { it.isChecked }

            if (radioSeleccionado == null) {

                Toast.makeText(
                    this,
                    "Seleccione un plan",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val plan: String
            val monto: Double

            when (radioSeleccionado.id) {

                R.id.rb6Cuotas -> {
                    plan = "6 CUOTAS"
                    monto = 19167.67
                }

                R.id.rb3Cuotas -> {
                    plan = "3 CUOTAS"
                    monto = 36666.67
                }

                R.id.rbContado -> {
                    plan = "CONTADO"
                    monto = 100000.0
                }

                else -> {
                    plan = ""
                    monto = 0.0
                }
            }

            val intent = Intent(
                this,
                PagoActivity::class.java
            )

            intent.putExtra(
                "TIPO_PAGO",
                "CUOTA"
            )

            intent.putExtra(
                "PLAN",
                plan
            )

            intent.putExtra(
                "PERSONA_ID",
                personaId
            )

            intent.putExtra(
                "PERSONA_NOMBRE",
                nombre
            )

            intent.putExtra(
                "PERSONA_APELLIDO",
                apellido
            )

            intent.putExtra(
                "TIPO_PERSONA",
                tipoPersona
            )

            intent.putExtra(
                "MONTO",
                monto
            )

            startActivity(intent)
        }
    }
}