package com.example.primerpoyectodam2026

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_settings)

        val cardLogOut = findViewById<LinearLayout>(R.id.cardLogOut)
        cardLogOut.setOnClickListener {
            val vista = layoutInflater.inflate(R.layout.dialog_logout, null) //objeto para convertir layout en vistas

            val dialog = AlertDialog.Builder(this)
                .setTitle("¿Desea cerrar sesión?")
                .setView(vista)
                .setPositiveButton("Salir") {_, _ ->
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)

                    Toast.makeText(this, "Saliendo...", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Cancelar", null)
                .create()
            dialog.show()
        }



        }
    }
