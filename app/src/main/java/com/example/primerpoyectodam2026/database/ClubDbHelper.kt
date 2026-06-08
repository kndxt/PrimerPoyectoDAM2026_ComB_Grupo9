package com.example.primerpoyectodam2026.database
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

    class ClubDbHelper(context: Context) : SQLiteOpenHelper(
        context,
        DATABASE_NAME,
        null,
        DATABASE_VERSION
    ) {

        companion object {
            private const val DATABASE_NAME = "club_deportivo.db"
            private const val DATABASE_VERSION = 5
        }

        override fun onCreate(db: SQLiteDatabase) {

            db.execSQL(
                """
            CREATE TABLE personas (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                apellido TEXT NOT NULL,
                dni TEXT NOT NULL UNIQUE,
                telefono TEXT,
                email TEXT NOT NULL UNIQUE,
                apto_fisico INTEGER NOT NULL,
                activo INTEGER NOT NULL,
                tipo_persona TEXT NOT NULL,
                fecha_inscripcion TEXT
            )
            """.trimIndent()
            )

            db.execSQL(
                """
            CREATE TABLE suscripciones (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                persona_id INTEGER NOT NULL,
                tipo_plan TEXT NOT NULL,
                fecha_inicio TEXT NOT NULL,
                fecha_vencimiento TEXT NOT NULL,
                FOREIGN KEY(persona_id) REFERENCES personas(id)
)
            """.trimIndent()
            )

            db.execSQL(
                """
            CREATE TABLE actividades (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                precio REAL NOT NULL
            )
            """.trimIndent()
            )

            db.execSQL(
                """
            CREATE TABLE pagos (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                persona_id INTEGER NOT NULL,
                fecha_pago TEXT NOT NULL,
                monto REAL NOT NULL,
                metodo_pago TEXT NOT NULL,
                tipo_pago TEXT NOT NULL,
                actividad_id INTEGER,
                suscripcion_id INTEGER,
                FOREIGN KEY(persona_id) REFERENCES personas(id),
                FOREIGN KEY(actividad_id) REFERENCES actividades(id),
                FOREIGN KEY(suscripcion_id) REFERENCES suscripciones(id)
            )
            """.trimIndent()
            )

            insertarActividadesIniciales(db)
            insertarPersonasIniciales(db)
            insertarSuscripcionesIniciales(db)
        }

        override fun onUpgrade(
            db: SQLiteDatabase,
            oldVersion: Int,
            newVersion: Int
        ) {
            db.execSQL("DROP TABLE IF EXISTS pagos")
            db.execSQL("DROP TABLE IF EXISTS suscripciones")
            db.execSQL("DROP TABLE IF EXISTS actividades")
            db.execSQL("DROP TABLE IF EXISTS personas")

            onCreate(db)
        }

        private fun insertarActividadesIniciales(db: SQLiteDatabase) {

            db.execSQL(
                """
                INSERT INTO actividades(nombre, precio)
                VALUES ('Musculacion', 50000)
                """
            )

            db.execSQL(
                """
                INSERT INTO actividades(nombre, precio)
                VALUES ('Voley', 55000)
                """
            )

            db.execSQL(
                """
                INSERT INTO actividades(nombre, precio)
                VALUES ('Boxeo', 50000)
                """
            )

            db.execSQL(
                """
                INSERT INTO actividades(nombre, precio)
                VALUES ('Natacion', 55000)
                """
            )

            db.execSQL(
                """
                INSERT INTO actividades(nombre, precio)
                VALUES ('Solo Maquinas', 50000)
                """
            )
        }

        private fun insertarPersonasIniciales(
            db: SQLiteDatabase
        ) {

            db.execSQL("""
        INSERT INTO personas
        (nombre,apellido,dni,telefono,email,apto_fisico,activo,tipo_persona,fecha_inscripcion)
        VALUES
        ('Administrador','Sistema','99999999','000000000','admin@club.com',1,1,'ADMIN',NULL)
        """.trimIndent())

            db.execSQL("""
        INSERT INTO personas
        (nombre,apellido,dni,telefono,email,apto_fisico,activo,tipo_persona,fecha_inscripcion)
        VALUES
        ('Juan','Perez','10100100','111111111','juan@gmail.com',1,1,'SOCIO','01/01/2026')
        """.trimIndent())

            db.execSQL("""
        INSERT INTO personas
        (nombre,apellido,dni,telefono,email,apto_fisico,activo,tipo_persona,fecha_inscripcion)
        VALUES
        ('Maria','Garcia','20200200','222222222','maria@gmail.com',1,1,'SOCIO','01/01/2026')
        """.trimIndent())

            db.execSQL("""
        INSERT INTO personas
        (nombre,apellido,dni,telefono,email,apto_fisico,activo,tipo_persona,fecha_inscripcion)
        VALUES
        ('Carlos','Rodriguez','30300300','333333333','carlos@gmail.com',1,1,'SOCIO','01/01/2026')
        """.trimIndent())

            db.execSQL("""
        INSERT INTO personas
        (nombre,apellido,dni,telefono,email,apto_fisico,activo,tipo_persona,fecha_inscripcion)
        VALUES
        ('Ana','Lopez','40400400','444444444','ana@gmail.com',1,1,'SOCIO','01/01/2026')
        """.trimIndent())

            db.execSQL("""
        INSERT INTO personas
        (nombre,apellido,dni,telefono,email,apto_fisico,activo,tipo_persona,fecha_inscripcion)
        VALUES
        ('Martin','Suarez','50500500','555555555','martin@gmail.com',1,1,'SOCIO','01/01/2026')
        """.trimIndent())

            db.execSQL("""
        INSERT INTO personas
        (nombre,apellido,dni,telefono,email,apto_fisico,activo,tipo_persona,fecha_inscripcion)
        VALUES
        ('Lucia','Fernandez','60600600','666666666','lucia@gmail.com',1,1,'SOCIO','01/01/2026')
        """.trimIndent())

            db.execSQL("""
        INSERT INTO personas
        (nombre,apellido,dni,telefono,email,apto_fisico,activo,tipo_persona,fecha_inscripcion)
        VALUES
        ('Diego','Ruiz','70700700','777777777','diego@gmail.com',1,1,'NO_SOCIO',NULL)
        """.trimIndent())

            db.execSQL("""
        INSERT INTO personas
        (nombre,apellido,dni,telefono,email,apto_fisico,activo,tipo_persona,fecha_inscripcion)
        VALUES
        ('Sofia','Torres','80800800','888888888','sofia@gmail.com',1,1,'NO_SOCIO',NULL)
        """.trimIndent())
        }

        private fun insertarSuscripcionesIniciales(
            db: SQLiteDatabase
        ) {
            db.execSQL("""
        INSERT INTO suscripciones
        (persona_id,tipo_plan,fecha_inicio,fecha_vencimiento)
        VALUES
        (2,'3 CUOTAS','01/05/2026','05/06/2026')
        """.trimIndent())

            db.execSQL("""
        INSERT INTO suscripciones
        (persona_id,tipo_plan,fecha_inicio,fecha_vencimiento)
        VALUES
        (3,'CONTADO','01/06/2026','01/07/2026')
        """.trimIndent())

            db.execSQL("""
        INSERT INTO suscripciones
        (persona_id,tipo_plan,fecha_inicio,fecha_vencimiento)
        VALUES
        (4,'6 CUOTAS','15/04/2026','15/05/2026')
        """.trimIndent())

            db.execSQL("""
        INSERT INTO suscripciones
        (persona_id,tipo_plan,fecha_inicio,fecha_vencimiento)
        VALUES
        (5,'CONTADO','01/06/2026','01/07/2026')
        """.trimIndent())

            db.execSQL("""
        INSERT INTO suscripciones
        (persona_id,tipo_plan,fecha_inicio,fecha_vencimiento)
        VALUES
        (6,'3 CUOTAS','05/05/2026','05/06/2026')
        """.trimIndent())
        }
    }