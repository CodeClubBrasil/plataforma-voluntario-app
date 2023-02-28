package com.example.codeclubapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner

class Cadastro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro)

        val stateSpinner: Spinner = findViewById (R.id.stateSpinner)
        val estados = resources.getStringArray(R.array.siglasEstados)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, estados)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        stateSpinner.adapter = adapter

        val continuar: Button = findViewById(R.id.botaoContinuar)

        continuar.setOnClickListener { val intent = Intent (this, Cadastro2::class.java)
            startActivity(intent) }

        val entrar: Button = findViewById(R.id.botaoEntrar)
        entrar.setOnClickListener { val intent = Intent (this, Login::class.java)
        startActivity(intent) }

    }
}