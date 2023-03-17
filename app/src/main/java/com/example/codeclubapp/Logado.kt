package com.example.codeclubapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class Logado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logado)

        val botaosair: Button = findViewById(R.id.buttonSair)
        botaosair.setOnClickListener { val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)}

    }
}