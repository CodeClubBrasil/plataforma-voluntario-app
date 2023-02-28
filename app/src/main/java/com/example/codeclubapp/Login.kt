package com.example.codeclubapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val fazercadastro: Button = findViewById(R.id.botaoFazerCadastro)
        fazercadastro.setOnClickListener { val intent = Intent (this, Cadastro::class.java)
        startActivity(intent)}

    }
}