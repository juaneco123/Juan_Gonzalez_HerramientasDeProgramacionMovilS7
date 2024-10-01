package com.example.juan_gonzalez_herramientasdeprogramacionmovils7

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class FeriadosActivity : AppCompatActivity() {
    private lateinit var apiCliente: ApiCliente
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_feriados)

        val usuario = intent.getStringExtra("usuario")
        findViewById<TextView>(R.id.texViewBienvenido).text = "Bienvenido: $usuario"

        recyclerView = findViewById(R.id.listViewFeriados)
        recyclerView.layoutManager = LinearLayoutManager(this)

        apiCliente = ApiCliente()

        val feriadosTotales = mutableListOf<String>()

        CoroutineScope(Dispatchers.Main).launch {
            try {
                val feriados2023 = apiCliente.obtenerFeriados(2023)
                val feriados2024 = apiCliente.obtenerFeriados(2024)

                feriadosTotales.addAll(feriados2023)
                feriadosTotales.addAll(feriados2024)

                if (feriadosTotales.isNotEmpty()) {
                    recyclerView.adapter = FeriadosAdapter(feriadosTotales)
                } else {
                    Toast.makeText(this@FeriadosActivity, "No se encontraron feriados", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@FeriadosActivity, "Error al obtener los feriados", Toast.LENGTH_SHORT).show()
            }
        }
    }
}