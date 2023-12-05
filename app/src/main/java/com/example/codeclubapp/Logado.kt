package com.example.codeclubapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.codeclubapp.src.BuscarCodeClub

class Logado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logado)

        val botaosair: Button = findViewById(R.id.buttonSair)
        botaosair.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val editarperfil: Button = findViewById(R.id.buttonEditarPerfil)
        editarperfil.setOnClickListener {
            val intent = Intent(this, Perfil::class.java)
            startActivity(intent)
        }


        val buscarCodeClub: Button = findViewById(R.id.buttonBuscarCodeClub)
        buscarCodeClub.setOnClickListener {
            startActivity(Intent(this, BuscarCodeClub::class.java))
        }
    }
}