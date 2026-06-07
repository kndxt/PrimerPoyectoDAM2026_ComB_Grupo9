package com.example.primerpoyectodam2026.dao

import android.content.ContentValues
import android.content.Context
import com.example.primerpoyectodam2026.database.ClubDbHelper
import com.example.primerpoyectodam2026.model.Pago

class PagoDao(context: Context) {

    private val dbHelper = ClubDbHelper(context)

    fun guardarPago(pago: Pago): Long {

        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {

            put("persona_id", pago.personaId)
            put("fecha_pago", pago.fechaPago)
            put("monto", pago.monto)
            put("metodo_pago", pago.metodoPago)
            put("tipo_pago", pago.tipoPago)

            put("actividad_id", pago.actividadId)
            put("suscripcion_id", pago.suscripcionId)
        }

        val resultado = db.insert(
            "pagos",
            null,
            values
        )

        db.close()

        return resultado
    }

    fun obtenerTodos(): List<Pago> {

        val listaPagos = mutableListOf<Pago>()

        val db = dbHelper.readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM pagos",
            null
        )

        if (cursor.moveToFirst()) {

            do {

                val pago = Pago(
                    id = cursor.getInt(
                        cursor.getColumnIndexOrThrow("id")
                    ),
                    personaId = cursor.getInt(
                        cursor.getColumnIndexOrThrow("persona_id")
                    ),
                    fechaPago = cursor.getString(
                        cursor.getColumnIndexOrThrow("fecha_pago")
                    ),
                    monto = cursor.getDouble(
                        cursor.getColumnIndexOrThrow("monto")
                    ),
                    metodoPago = cursor.getString(
                        cursor.getColumnIndexOrThrow("metodo_pago")
                    ),
                    tipoPago = cursor.getString(
                        cursor.getColumnIndexOrThrow("tipo_pago")
                    ),
                    actividadId =
                        if (
                            cursor.isNull(
                                cursor.getColumnIndexOrThrow("actividad_id")
                            )
                        )
                            null
                        else
                            cursor.getInt(
                                cursor.getColumnIndexOrThrow("actividad_id")
                            ),
                    suscripcionId =
                        if (
                            cursor.isNull(
                                cursor.getColumnIndexOrThrow("suscripcion_id")
                            )
                        )
                            null
                        else
                            cursor.getInt(
                                cursor.getColumnIndexOrThrow("suscripcion_id")
                            )
                )

                listaPagos.add(pago)

            } while (cursor.moveToNext())
        }

        cursor.close()
        db.close()

        return listaPagos
    }
}