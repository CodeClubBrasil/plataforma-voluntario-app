package com.example.codeclubapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.codeclubapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*configuração view binding*/
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        /* indo para cadastro */
        binding.btnSignUp.setOnClickListener {
            startActivity(Intent(this, SignUp01::class.java))
        }

        binding.botaoEntrar.setOnClickListener {
            startActivity(Intent(this,Login::class.java))
        }
    }
}