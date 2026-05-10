package com.example.primerpoyectodam2026

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button

class DisciplinasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_disciplinas)

        val pagoDiaria = findViewById<Button>(R.id.pagoDiaria)
        pagoDiaria.setOnClickListener {
            val intent = Intent(this, PagoActivity::class.java)
            startActivity(intent)
        }
    }
}