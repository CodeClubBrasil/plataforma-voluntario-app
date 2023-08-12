package com.example.codeclubapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val cadastrar: Button = findViewById(R.id.botaoCadastrar)

        cadastrar.setOnClickListener { val intent = Intent (this, Cadastro::class.java)
            startActivity(intent) }


        val entrar: Button = findViewById(R.id.botaoEntrar)

        entrar.setOnClickListener { val intent = Intent(this, Login::class.java)
        startActivity(intent) }

    }
}