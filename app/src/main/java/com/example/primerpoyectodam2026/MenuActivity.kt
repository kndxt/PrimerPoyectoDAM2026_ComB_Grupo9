package com.example.primerpoyectodam2026

import android.os.Bundle
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val cardSocios = findViewById<LinearLayout>(R.id.cardSocios)
        cardSocios.setOnClickListener {
            Toast.makeText(this, "Entrando a Socios", Toast.LENGTH_SHORT).show()

            val vista = layoutInflater.inflate(R.layout.dialog_socio, null) //objeto para convertir layout en vistas

            val etNombre = vista.findViewById<EditText>(R.id.etNombre)
            val etDni = vista.findViewById<EditText>(R.id.etDni)

            val dialog = AlertDialog.Builder(this)
                .setTitle("Nuevo Socio")
                .setView(vista)
                .setPositiveButton("Guardar") {_, _ ->
                    val nombreToString = etNombre.text.toString()

                    Toast.makeText(this, "Socio: $nombreToString", Toast.LENGTH_LONG).show()
                }
                .setNegativeButton("Cancelar", null)
                .create()
            dialog.show()

        }
        
    }
}
