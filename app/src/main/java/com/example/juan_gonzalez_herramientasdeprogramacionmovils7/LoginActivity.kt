package com.example.juan_gonzalez_herramientasdeprogramacionmovils7

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        preferences = getSharedPreferences("user_prefs", MODE_PRIVATE)

        val usuario = preferences.getString("usuario", null)
        if (usuario != null) {
            irAFeriados(usuario)
        }

        findViewById<Button>(R.id.buttonLogin).setOnClickListener { login() }
    }

    private fun login() {
        val usuario = findViewById<EditText>(R.id.editTextUsuario).text.toString()
        val contraseña = findViewById<EditText>(R.id.editTextContraseña).text.toString()

        if (usuario.isNotEmpty() && contraseña.isNotEmpty()) {
            guardarUsuario(usuario)
            irAFeriados(usuario)
        } else {
            Toast.makeText(this, "Por favor, ingrese usuario y contraseña", Toast.LENGTH_SHORT).show()
        }
    }

    private fun guardarUsuario(usuario: String) {
        val editor = preferences.edit()
        editor.putString("usuario", usuario)
        editor.apply()
    }

    private fun irAFeriados(usuario: String) {
        val intent = Intent(this, FeriadosActivity::class.java)
        intent.putExtra("usuario", usuario)
        startActivity(intent)
        finish()
    }
}
