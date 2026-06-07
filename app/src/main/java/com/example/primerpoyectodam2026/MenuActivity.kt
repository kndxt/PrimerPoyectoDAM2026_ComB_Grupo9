package com.example.primerpoyectodam2026

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.CheckBox
import android.widget.Spinner
import com.example.primerpoyectodam2026.dao.PersonaDao
import com.example.primerpoyectodam2026.model.Persona
import com.example.primerpoyectodam2026.dao.SuscripcionDao

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val tipoPersona =
            intent.getStringExtra("TIPO_PERSONA")

        val personaId =
            intent.getIntExtra("PERSONA_ID", 0)

        val nombre =
            intent.getStringExtra("PERSONA_NOMBRE") ?: ""

        val apellido =
            intent.getStringExtra("PERSONA_APELLIDO") ?: ""

        val email =
            intent.getStringExtra("PERSONA_EMAIL") ?: ""

        val telefono =
            intent.getStringExtra("PERSONA_TELEFONO") ?: ""

        val cardSocios =
            findViewById<LinearLayout>(R.id.cardRegistro)

        val cardVencimientos =
            findViewById<LinearLayout>(R.id.cardVencimientos)

        val cardAbono =
            findViewById<LinearLayout>(R.id.cardAbono)

        val btnIrConfig =
            findViewById<Button>(R.id.bntConfig)

        val cardCredencial =
            findViewById<LinearLayout>(R.id.cardCredencial)

        when (tipoPersona) {

            "SOCIO" -> {

                cardSocios.visibility =
                    android.view.View.GONE

                cardVencimientos.visibility =
                    android.view.View.GONE

                cardAbono.visibility =
                    android.view.View.GONE

                btnIrConfig.visibility =
                    android.view.View.GONE
            }

            "ADMIN" -> {

                cardCredencial.visibility =
                    android.view.View.GONE
            }
        }

        cardSocios.setOnClickListener {

            val vista =
                layoutInflater.inflate(
                    R.layout.dialog_registro,
                    null
                )

            val etNombre =
                vista.findViewById<EditText>(R.id.etNombre)

            val etApellido =
                vista.findViewById<EditText>(R.id.etApellido)

            val etDni =
                vista.findViewById<EditText>(R.id.etDni)

            val etEmail =
                vista.findViewById<EditText>(R.id.etEmail)

            val cbAptoFisico =
                vista.findViewById<CheckBox>(R.id.etAptoFisico)

            val spinner =
                vista.findViewById<Spinner>(R.id.idOpciones)

            val personaDao =
                PersonaDao(this)

            AlertDialog.Builder(this)
                .setTitle("Registro")
                .setView(vista)
                .setPositiveButton("Guardar") { _, _ ->

                    val tipoPersonaNueva =
                        when (spinner.selectedItem.toString()) {
                            "Socio" -> "SOCIO"
                            "No socio" -> "NO_SOCIO"
                            else -> ""
                        }

                    val persona = Persona(
                        nombre = etNombre.text.toString(),
                        apellido = etApellido.text.toString(),
                        dni = etDni.text.toString(),
                        telefono = "",
                        email = etEmail.text.toString(),
                        aptoFisico = cbAptoFisico.isChecked,
                        activo = true,
                        tipoPersona = tipoPersonaNueva,
                        fechaInscripcion = null
                    )

                    val resultado =
                        personaDao.guardarPersona(persona)

                    if (resultado > 0) {

                        Toast.makeText(
                            this,
                            "Persona registrada correctamente",
                            Toast.LENGTH_SHORT
                        ).show()

                    } else {

                        Toast.makeText(
                            this,
                            "Error al registrar",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                .setNegativeButton("Cancelar", null)
                .show()
        }

        cardVencimientos.setOnClickListener {

            val suscripcionDao =
                SuscripcionDao(this)

            val listaPersonas =
                suscripcionDao.obtenerVencimientos()

            if (listaPersonas.isEmpty()) {

                Toast.makeText(
                    this,
                    "No existen vencimientos para el día de hoy.",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val vista =
                layoutInflater.inflate(
                    R.layout.dialog_vencimientos,
                    null
                )

            val lvPersonas =
                vista.findViewById<ListView>(
                    R.id.lvPersonas
                )

            val adapter = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                listaPersonas
            )

            lvPersonas.adapter = adapter

            val dialog = AlertDialog.Builder(this)
                .setTitle("Vencimientos de Hoy")
                .setView(vista)
                .setPositiveButton("Cerrar", null)
                .create()

            dialog.show()
        }

        btnIrConfig.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        cardAbono.setOnClickListener {
            val intent = Intent(this, MenuAbonarActivity::class.java)
            startActivity(intent)
        }

        cardCredencial.setOnClickListener {

            val intent = Intent(
                this,
                CredencialActivity::class.java
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
                "PERSONA_EMAIL",
                email
            )

            intent.putExtra(
                "PERSONA_TELEFONO",
                telefono
            )

            startActivity(intent)
        }

    }
}
