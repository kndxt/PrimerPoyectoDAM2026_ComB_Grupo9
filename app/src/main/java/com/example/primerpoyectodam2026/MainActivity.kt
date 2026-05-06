package com.example.primerpoyectodam2026

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //class MainActivity : AppCompatActivity() {
        //    override fun onCreate(savedInstanceState: Bundle?) {
        //        super.onCreate(savedInstanceState)
        //        setContentView(R.layout.activity_main)
        //
        //        val btnIrMenu = findViewById<Button>(id = R.id.btnIrMenu)
        //        btnIrMenu.setOnClickListener {
        //            val Intent = Intent(packageContext = this, cls = MenuActivity::class.java)
        //        }
        //    }
        //}

        val btnIrMenu = findViewById<Button>(R.id.btnIrMenu)
        btnIrMenu.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
                 }

    }
}