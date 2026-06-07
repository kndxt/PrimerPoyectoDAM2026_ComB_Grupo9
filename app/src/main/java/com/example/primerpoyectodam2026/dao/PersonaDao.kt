package com.example.primerpoyectodam2026.dao

import android.content.ContentValues
import android.content.Context
import com.example.primerpoyectodam2026.database.ClubDbHelper
import com.example.primerpoyectodam2026.model.Persona

class PersonaDao(context: Context) {

    private val dbHelper = ClubDbHelper(context)

    fun guardarPersona(persona: Persona): Long {

        val db = dbHelper.writableDatabase

        val values = ContentValues().apply {

            put("nombre", persona.nombre)
            put("apellido", persona.apellido)
            put("dni", persona.dni)
            put("telefono", persona.telefono)
            put("email", persona.email)
            put("apto_fisico", if (persona.aptoFisico) 1 else 0)
            put("activo", if (persona.activo) 1 else 0)
            put("tipo_persona", persona.tipoPersona)
            put("fecha_inscripcion", persona.fechaInscripcion)
        }

        val resultado = db.insert(
            "personas",
            null,
            values
        )

        db.close()

        return resultado
    }

    fun buscarPorDni(dni: String): Persona? {

        val db = dbHelper.readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM personas WHERE dni = ?",
            arrayOf(dni)
        )

        var persona: Persona? = null

        if (cursor.moveToFirst()) {

            persona = Persona(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                apellido = cursor.getString(cursor.getColumnIndexOrThrow("apellido")),
                dni = cursor.getString(cursor.getColumnIndexOrThrow("dni")),
                telefono = cursor.getString(cursor.getColumnIndexOrThrow("telefono")),
                email = cursor.getString(cursor.getColumnIndexOrThrow("email")),
                aptoFisico = cursor.getInt(
                    cursor.getColumnIndexOrThrow("apto_fisico")
                ) == 1,
                activo = cursor.getInt(
                    cursor.getColumnIndexOrThrow("activo")
                ) == 1,
                tipoPersona = cursor.getString(
                    cursor.getColumnIndexOrThrow("tipo_persona")
                ),
                fechaInscripcion = cursor.getString(
                    cursor.getColumnIndexOrThrow("fecha_inscripcion")
                )
            )
        }

        cursor.close()
        db.close()

        return persona
    }

    fun buscarPorEmail(email: String): Persona? {

        val db = dbHelper.readableDatabase

        val cursor = db.rawQuery(
            "SELECT * FROM personas WHERE email = ?",
            arrayOf(email)
        )

        var persona: Persona? = null

        if (cursor.moveToFirst()) {

            persona = Persona(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                apellido = cursor.getString(cursor.getColumnIndexOrThrow("apellido")),
                dni = cursor.getString(cursor.getColumnIndexOrThrow("dni")),
                telefono = cursor.getString(cursor.getColumnIndexOrThrow("telefono")),
                email = cursor.getString(cursor.getColumnIndexOrThrow("email")),
                aptoFisico = cursor.getInt(
                    cursor.getColumnIndexOrThrow("apto_fisico")
                ) == 1,
                activo = cursor.getInt(
                    cursor.getColumnIndexOrThrow("activo")
                ) == 1,
                tipoPersona = cursor.getString(
                    cursor.getColumnIndexOrThrow("tipo_persona")
                ),
                fechaInscripcion = cursor.getString(
                    cursor.getColumnIndexOrThrow("fecha_inscripcion")
                )
            )
        }

        cursor.close()
        db.close()

        return persona
    }
}