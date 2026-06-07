package com.example.primerpoyectodam2026

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class PagoConfirmadoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pago_confirmado)

        val tipoPago = intent.getStringExtra("TIPO_PAGO")
        val actividad = intent.getStringExtra("ACTIVIDAD")

        val personaId = intent.getIntExtra("PERSONA_ID", 0)
        val nombre = intent.getStringExtra("PERSONA_NOMBRE") ?: ""
        val apellido = intent.getStringExtra("PERSONA_APELLIDO") ?: ""

        val metodoPago =
            intent.getStringExtra("METODO_PAGO") ?: "-"

        val fechaPago =
            intent.getStringExtra("FECHA_PAGO") ?: "-"

        val monto =
            intent.getDoubleExtra("MONTO", 0.0)

        val tvEtiquetaPrincipal =
            findViewById<TextView>(R.id.tvEtiquetaPrincipal)

        val tvSocio =
            findViewById<TextView>(R.id.tvSocio)

        val tvEtiquetaSecundaria =
            findViewById<TextView>(R.id.tvEtiquetaSecundaria)

        val tvNumeroSocio =
            findViewById<TextView>(R.id.tvNumeroSocio)

        val tvMonto =
            findViewById<TextView>(R.id.tvMonto)

        val tvMontoPrincipal =
            findViewById<TextView>(R.id.tvMontoPrincipal)

        val tvMetodo =
            findViewById<TextView>(R.id.tvMetodo)

        val tvFecha =
            findViewById<TextView>(R.id.tvFecha)

        val tvVencimiento =
            findViewById<TextView>(R.id.tvVencimiento)

        val layoutVencimiento =
            findViewById<LinearLayout>(R.id.layoutVencimiento)

        val simbolos = DecimalFormatSymbols().apply {
            groupingSeparator = '.'
        }

        val formatoMonto = DecimalFormat(
            "#,###",
            simbolos
        )

        if (tipoPago == "CUOTA") {

            tvEtiquetaPrincipal.text = "Socio:"
            tvSocio.text = "$nombre $apellido"

            tvEtiquetaSecundaria.text = "N° Socio:"
            tvNumeroSocio.text = personaId.toString()

            val formatoFecha = SimpleDateFormat(
                "dd/MM/yyyy",
                Locale.getDefault()
            )

            val fecha = formatoFecha.parse(fechaPago)

            if (fecha != null) {

                val calendar = Calendar.getInstance()

                calendar.time = fecha

                calendar.add(
                    Calendar.MONTH,
                    1
                )

                tvVencimiento.text =
                    formatoFecha.format(calendar.time)
            }

        } else {

            tvEtiquetaPrincipal.text = "Cliente:"
            tvSocio.text = "$nombre $apellido"

            tvEtiquetaSecundaria.text = "Actividad:"
            tvNumeroSocio.text = actividad ?: "-"

            layoutVencimiento.visibility = View.GONE
        }

        val montoFormateado =
            "$${formatoMonto.format(monto)}"

        tvMonto.text = montoFormateado
        tvMontoPrincipal.text = montoFormateado

        tvMetodo.text = metodoPago
        tvFecha.text = fechaPago

        val btnIrMenu =
            findViewById<Button>(R.id.btnIrMenu)

        btnIrMenu.setOnClickListener {

            val intent = Intent(
                this,
                MenuActivity::class.java
            )

            startActivity(intent)

            finish()
        }
    }
}