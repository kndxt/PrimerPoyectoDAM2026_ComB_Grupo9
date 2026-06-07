package com.example.primerpoyectodam2026

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class DisciplinasActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disciplinas)

        val personaId = intent.getIntExtra("PERSONA_ID", 0)
        val nombre = intent.getStringExtra("PERSONA_NOMBRE")
        val apellido = intent.getStringExtra("PERSONA_APELLIDO")
        val tipoPersona = intent.getStringExtra("TIPO_PERSONA")

        val rbMusculacion =
            findViewById<RadioButton>(R.id.rbMusculacion)

        val rbVoley =
            findViewById<RadioButton>(R.id.rbVoley)

        val rbBoxeo =
            findViewById<RadioButton>(R.id.rbBoxeo)

        val rbNatacion =
            findViewById<RadioButton>(R.id.rbNatacion)

        val rbSoloMaquinas =
            findViewById<RadioButton>(R.id.rbSoloMaquinas)

        val radios = listOf(
            rbMusculacion,
            rbVoley,
            rbBoxeo,
            rbNatacion,
            rbSoloMaquinas
        )

        radios.forEach { radio ->

            radio.setOnClickListener {

                radios.forEach {
                    it.isChecked = false
                }

                radio.isChecked = true
            }
        }

        val pagoDiaria =
            findViewById<Button>(R.id.pagoDiaria)

        pagoDiaria.setOnClickListener {

            val radioSeleccionado =
                radios.find { it.isChecked }

            if (radioSeleccionado == null) {

                Toast.makeText(
                    this,
                    "Seleccione una actividad",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val actividad = when (radioSeleccionado.id) {

                R.id.rbMusculacion -> "Musculacion"
                R.id.rbVoley -> "Voley"
                R.id.rbBoxeo -> "Boxeo"
                R.id.rbNatacion -> "Natacion"
                R.id.rbSoloMaquinas -> "Solo Maquinas"

                else -> ""
            }

            val intent = Intent(
                this,
                PagoActivity::class.java
            )

            intent.putExtra(
                "ACTIVIDAD",
                actividad
            )

            intent.putExtra(
                "TIPO_PAGO",
                "ACTIVIDAD"
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

            startActivity(intent)
        }
    }
}