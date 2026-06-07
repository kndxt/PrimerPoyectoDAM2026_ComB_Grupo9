package com.example.primerpoyectodam2026

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.primerpoyectodam2026.dao.PersonaDao

class BuscarPersonaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buscar_persona)

        val txtDni = findViewById<EditText>(R.id.txtDocumentoPersona)
        val lvPersonas = findViewById<ListView>(R.id.lvPersonas)
        val btnBuscar = findViewById<Button>(R.id.btnBuscarPersona)

        val personaDao = PersonaDao(this)

        val tipoPago = intent.getStringExtra("TIPO_PAGO")

        btnBuscar.setOnClickListener {

            val dni = txtDni.text.toString()

            if (dni.isBlank()) {

                Toast.makeText(
                    this,
                    "Ingrese un DNI",
                    Toast.LENGTH_SHORT
                ).show()

                lvPersonas.adapter = null

                return@setOnClickListener
            }

            if (dni.length != 8 || !dni.all { it.isDigit() }) {

                Toast.makeText(
                    this,
                    "El DNI debe contener exactamente 8 números",
                    Toast.LENGTH_SHORT
                ).show()

                lvPersonas.adapter = null

                return@setOnClickListener
            }

            val persona = personaDao.buscarPorDni(dni)

            if (persona != null) {

                if (
                    tipoPago == "CUOTA" &&
                    persona.tipoPersona != "SOCIO"
                ) {

                    Toast.makeText(
                        this,
                        "El DNI ingresado no pertenece a un socio",
                        Toast.LENGTH_SHORT
                    ).show()

                    lvPersonas.adapter = null

                    return@setOnClickListener
                }

                if (
                    tipoPago == "ACTIVIDAD" &&
                    persona.tipoPersona != "NO_SOCIO"
                ) {

                    Toast.makeText(
                        this,
                        "El DNI ingresado no pertenece a un no socio",
                        Toast.LENGTH_SHORT
                    ).show()

                    lvPersonas.adapter = null

                    return@setOnClickListener
                }

                val resultado = listOf(
                    "Nombre: ${persona.nombre}",
                    "Apellido: ${persona.apellido}",
                    "DNI: ${persona.dni}",
                    "Tipo: ${persona.tipoPersona}"
                )

                lvPersonas.adapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_list_item_1,
                    resultado
                )

                val destino =
                    if (tipoPago == "CUOTA")
                        SuscripcionActivity::class.java
                    else
                        DisciplinasActivity::class.java

                val intent = Intent(this, destino)

                intent.putExtra("PERSONA_ID", persona.id)
                intent.putExtra("PERSONA_NOMBRE", persona.nombre)
                intent.putExtra("PERSONA_APELLIDO", persona.apellido)
                intent.putExtra("TIPO_PERSONA", persona.tipoPersona)

                startActivity(intent)

            } else {

                Toast.makeText(
                    this,
                    "Persona no encontrada",
                    Toast.LENGTH_SHORT
                ).show()

                lvPersonas.adapter = null
            }
        }
    }
}