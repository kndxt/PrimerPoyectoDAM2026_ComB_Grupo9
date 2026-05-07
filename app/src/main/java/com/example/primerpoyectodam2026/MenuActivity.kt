package com.example.primerpoyectodam2026

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        val cardSocios = findViewById<LinearLayout>(R.id.cardRegistro)
        cardSocios.setOnClickListener {
            //Toast.makeText(this, "Entrando a Socios", Toast.LENGTH_SHORT).show()

            val vista = layoutInflater.inflate(R.layout.dialog_registro, null) //objeto para convertir layout en vistas

            val etNombre = vista.findViewById<EditText>(R.id.etNombre)

            val dialog = AlertDialog.Builder(this)
                .setTitle("Registro")
                .setView(vista)
                .setPositiveButton("Guardar") {_, _ ->
                    val nombreToString = etNombre.text.toString()



                    Toast.makeText(this, "Socio: $nombreToString", Toast.LENGTH_LONG).show()
                }
                .setNegativeButton("Cancelar", null)
                .create()
            dialog.show()

        }

        val cardVencimientos = findViewById<LinearLayout>(R.id.cardVencimientos)
        cardVencimientos.setOnClickListener {
            // Datos harcodeados (Simulación de BD)
            val listaPersonas = listOf(
                "Juan Pérez - Pendiente",
                "María García - Pagado",
                "Carlos Rodríguez - Vencido",
                "Ana Martínez - Pendiente",
                "Luis Fernández - Pagado"
            )

            val vista = layoutInflater.inflate(R.layout.dialog_vencimientos, null)
            val lvPersonas = vista.findViewById<ListView>(R.id.lvPersonas)

            // Adaptador para la lista
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listaPersonas)
            lvPersonas.adapter = adapter

            val dialog = AlertDialog.Builder(this)
                .setTitle("Vencimientos Próximos")
                .setView(vista)
                .setPositiveButton("Cerrar", null)
                .create()
            dialog.show()
        }
    }
}
