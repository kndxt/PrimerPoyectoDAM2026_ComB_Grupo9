package com.example.primerpoyectodam2026.dao

import android.content.ContentValues
import android.content.Context
import com.example.primerpoyectodam2026.database.ClubDbHelper
import com.example.primerpoyectodam2026.model.Suscripcion

class SuscripcionDao(context: Context) {

    private val dbHelper = ClubDbHelper(context)

    fun guardarSuscripcion(
        suscripcion: Suscripcion
    ): Long {

        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {

            put(
                "persona_id",
                suscripcion.personaId
            )

            put(
                "tipo_plan",
                suscripcion.tipoPlan
            )

            put(
                "fecha_inicio",
                suscripcion.fechaInicio
            )

            put(
                "fecha_vencimiento",
                suscripcion.fechaVencimiento
            )
        }

        val resultado = db.insert(
            "suscripciones",
            null,
            values
        )

        db.close()

        return resultado
    }

    fun obtenerVencimientos(): List<String> {

        val lista = mutableListOf<String>()

        val db = dbHelper.readableDatabase

        val formatoFecha =
            java.text.SimpleDateFormat(
                "dd/MM/yyyy",
                java.util.Locale.getDefault()
            )

        val hoy =
            formatoFecha.parse(
                formatoFecha.format(
                    java.util.Date()
                )
            )

        val query = """
        SELECT
            p.nombre,
            p.apellido,
            s.fecha_vencimiento
        FROM suscripciones s
        INNER JOIN personas p
            ON p.id = s.persona_id
        WHERE p.tipo_persona = 'SOCIO'
        """.trimIndent()

        val cursor =
            db.rawQuery(query, null)

        while (cursor.moveToNext()) {

            val nombre =
                cursor.getString(0)

            val apellido =
                cursor.getString(1)

            val fechaVencimientoTexto =
                cursor.getString(2)

            val fechaVencimiento =
                formatoFecha.parse(
                    fechaVencimientoTexto
                )

            if (
                fechaVencimiento != null &&
                hoy != null &&
                !fechaVencimiento.after(hoy)
            ) {

                lista.add(
                    "$nombre $apellido - Vence: $fechaVencimientoTexto"
                )
            }
        }

        cursor.close()
        db.close()

        return lista
    }

    fun obtenerEstadoCuota(
        personaId: Int
    ): String {

        val db = dbHelper.readableDatabase

        val cursor = db.rawQuery(
            """
        SELECT fecha_vencimiento
        FROM suscripciones
        WHERE persona_id = ?
        """.trimIndent(),
            arrayOf(personaId.toString())
        )

        if (!cursor.moveToFirst()) {

            cursor.close()
            db.close()

            return "Sin suscripción"
        }

        val fechaVencimientoTexto =
            cursor.getString(0)

        cursor.close()
        db.close()

        val formatoFecha =
            java.text.SimpleDateFormat(
                "dd/MM/yyyy",
                java.util.Locale.getDefault()
            )

        val fechaHoy =
            formatoFecha.parse(
                formatoFecha.format(
                    java.util.Date()
                )
            )

        val fechaVencimiento =
            formatoFecha.parse(
                fechaVencimientoTexto
            )

        return if (
            fechaHoy != null &&
            fechaVencimiento != null &&
            !fechaVencimiento.before(fechaHoy)
        ) {

            "AL DÍA (vence $fechaVencimientoTexto)"

        } else {

            "VENCIDA (venció $fechaVencimientoTexto)"
        }
    }

}