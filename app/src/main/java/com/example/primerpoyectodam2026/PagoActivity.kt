package com.example.primerpoyectodam2026

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.primerpoyectodam2026.dao.ActividadDao
import com.example.primerpoyectodam2026.dao.PagoDao
import com.example.primerpoyectodam2026.dao.SuscripcionDao
import com.example.primerpoyectodam2026.model.Pago
import com.example.primerpoyectodam2026.model.Suscripcion
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class PagoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pago)

        val tipoPago = intent.getStringExtra("TIPO_PAGO")
        val actividad = intent.getStringExtra("ACTIVIDAD")
        val plan = intent.getStringExtra("PLAN")

        val personaId = intent.getIntExtra("PERSONA_ID", 0)
        val nombre = intent.getStringExtra("PERSONA_NOMBRE")
        val apellido = intent.getStringExtra("PERSONA_APELLIDO")
        val tipoPersona = intent.getStringExtra("TIPO_PERSONA")

        val rgMetodoPago =
            findViewById<RadioGroup>(R.id.rgMetodoPago)

        val btnSiguiente =
            findViewById<Button>(R.id.btnSiguiente)

        val pagoDao = PagoDao(this)

        val actividadDao = ActividadDao(this)

        val suscripcionDao = SuscripcionDao(this)

        btnSiguiente.setOnClickListener {

            if (rgMetodoPago.checkedRadioButtonId == -1) {

                Toast.makeText(
                    this,
                    "Seleccione un método de pago",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            val radioSeleccionado =
                findViewById<RadioButton>(
                    rgMetodoPago.checkedRadioButtonId
                )

            val metodoPago =
                radioSeleccionado.text.toString()

            val fechaActual =
                SimpleDateFormat(
                    "dd/MM/yyyy",
                    Locale.getDefault()
                ).format(Date())

            val monto =

                if (tipoPago == "CUOTA") {

                    when (plan) {

                        "6 CUOTAS" -> 19167.67
                        "3 CUOTAS" -> 36666.67
                        "CONTADO" -> 100000.0

                        else -> 0.0
                    }

                } else {

                    val actividadSeleccionada =
                        actividad?.let {
                            actividadDao.buscarPorNombre(it)
                        }

                    actividadSeleccionada?.precio ?: 0.0
                }

            if (tipoPago == "CUOTA") {

                val formatoFecha =
                    SimpleDateFormat(
                        "dd/MM/yyyy",
                        Locale.getDefault()
                    )

                val fechaInicio =
                    formatoFecha.parse(fechaActual)

                if (fechaInicio != null) {

                    val calendar =
                        Calendar.getInstance()

                    calendar.time = fechaInicio

                    calendar.add(
                        Calendar.MONTH,
                        1
                    )

                    val fechaVencimiento =
                        formatoFecha.format(
                            calendar.time
                        )

                    val suscripcion = Suscripcion(
                        personaId = personaId,
                        tipoPlan = plan ?: "",
                        fechaInicio = fechaActual,
                        fechaVencimiento = fechaVencimiento
                    )

                    suscripcionDao.guardarSuscripcion(
                        suscripcion
                    )
                }
            }

            val pago = Pago(
                personaId = personaId,
                fechaPago = fechaActual,
                monto = monto,
                metodoPago = metodoPago,
                tipoPago = tipoPago ?: "",
                actividadId = null,
                suscripcionId = null
            )

            pagoDao.guardarPago(pago)

            val intent = Intent(
                this,
                PagoConfirmadoActivity::class.java
            )

            intent.putExtra(
                "TIPO_PAGO",
                tipoPago
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

            intent.putExtra(
                "METODO_PAGO",
                metodoPago
            )

            intent.putExtra(
                "FECHA_PAGO",
                fechaActual
            )

            intent.putExtra(
                "MONTO",
                monto
            )

            if (actividad != null) {

                intent.putExtra(
                    "ACTIVIDAD",
                    actividad
                )
            }

            if (plan != null) {

                intent.putExtra(
                    "PLAN",
                    plan
                )
            }

            startActivity(intent)
        }
    }
}