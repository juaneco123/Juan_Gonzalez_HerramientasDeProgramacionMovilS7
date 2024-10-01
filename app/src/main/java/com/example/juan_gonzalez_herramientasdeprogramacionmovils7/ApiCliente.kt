package com.example.juan_gonzalez_herramientasdeprogramacionmovils7

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import org.json.JSONArray

class ApiCliente {


    suspend fun obtenerFeriados(año: Int): List<String> {
        val url = "https://apis.digital.gob.cl/fl/feriados/$año"
        return withContext(Dispatchers.IO) {
            val client = OkHttpClient()
            val request = Request.Builder().url(url).build()
            val response: Response = client.newCall(request).execute()
            val feriadosList = mutableListOf<String>()

            if (response.isSuccessful) {
                val jsonData = response.body()?.string()
                val jsonArray = JSONArray(jsonData)
                for (i in 0 until jsonArray.length()) {
                    val jsonObject = jsonArray.getJSONObject(i)
                    val nombre = jsonObject.getString("nombre")
                    feriadosList.add(nombre)
                }
            }
            feriadosList
        }
    }
}
