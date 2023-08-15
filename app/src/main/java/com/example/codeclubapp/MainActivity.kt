package com.example.codeclubapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.viewModels
import com.example.codeclubapp.databinding.ActivityMainBinding
import com.example.codeclubapp.src.ui.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    /*
    * Para funcionar o by viewModels() é preciso adicionar a dependencia do fragment ktx
    * */
    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*configuração view binding*/
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.mostrarTexto()
        viewModel.texto.observe(this) { _text ->
        }

        /*DEVELOPMENT BRANCH */
        val novoBotao = binding.botaoCadastrar

        novoBotao.setOnClickListener {
            val intent = Intent(this, Cadastro::class.java)
            startActivity(intent)
        }

        val entrar: Button = findViewById(R.id.botaoEntrar)

        entrar.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }
    }
}