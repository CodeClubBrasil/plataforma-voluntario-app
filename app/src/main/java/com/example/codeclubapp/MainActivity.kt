package com.example.codeclubapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.codeclubapp.databinding.ActivityMainBinding
import com.example.codeclubapp.src.ui.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    //erro
    private val viewModel: MainActivityViewModel by viewModels<MainActivityViewModel>()
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*configuração view binding*/
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.mostrarTexto()
        viewModel.texto.observe(this) { _text ->
            Toast.makeText(this, _text, Toast.LENGTH_LONG)
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