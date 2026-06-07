package com.example.primerpoyectodam2026.dao

import android.content.Context
import com.example.primerpoyectodam2026.database.ClubDbHelper
import com.example.primerpoyectodam2026.model.Actividad

class ActividadDao(context: Context) {

    private val dbHelper = ClubDbHelper(context)

    fun buscarPorNombre(nombre: String): Actividad? {

        val db = dbHelper.readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM actividades WHERE nombre = ?",
            arrayOf(nombre)
        )

        var actividad: Actividad? = null

        if (cursor.moveToFirst()) {

            actividad = Actividad(
                id = cursor.getInt(
                    cursor.getColumnIndexOrThrow("id")
                ),
                nombre = cursor.getString(
                    cursor.getColumnIndexOrThrow("nombre")
                ),
                precio = cursor.getDouble(
                    cursor.getColumnIndexOrThrow("precio")
                )
            )
        }

        cursor.close()
        db.close()

        return actividad
    }
}