package com.example.primerpoyectodam2026

import android.content.Intent
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class MenuAbonarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_abonar)

        val cuotaMensual = findViewById<LinearLayout>(R.id.card_socios)
        cuotaMensual.setOnClickListener {
            val intent = Intent(this, BuscarSocioActivity::class.java)
            startActivity(intent)
        }

        val ActiDiaria = findViewById<LinearLayout>(R.id.ActiDiaria)
        ActiDiaria.setOnClickListener {
            val intent = Intent(this, BuscarNoSocioActivity::class.java)
            startActivity(intent)
        }
    }
}
