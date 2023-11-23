package com.example.codeclubapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import com.example.codeclubapp.databinding.ActivityLoginBinding
import com.example.codeclubapp.src.classesModelos.user.UserCodeClub

private const val TAG = "TAG"
class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        val userCodeClubA = UserCodeClub(name = "Pedro", password = "1234")
//        val userCodeClubB = UserCodeClub(name = "William", password = "1234")
//        val listaLoginFake = mutsableListOf<UserCodeClub>(UserCodeClub())
//
//
//        listaLoginFake.addAll(listOf(userCodeClubA, userCodeClubB))
//        Log.i(TAG, "A listaFake é:  $listaLoginFake")


        var nome = ""
        var senha = ""
        binding.editTextNomeLogin.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                nome = s.toString()
                Log.i(TAG, " nome digitado no editText foi: $nome ")
            }
            override fun afterTextChanged(s: Editable?) { }
        })

        binding.editTextSenhaLogin.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) { }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                senha = s.toString()
                Log.i(TAG, " Senha digitada no editText foi: $senha ")
            }

            override fun afterTextChanged(s: Editable?) { }

        })


        val fazercadastro: Button = findViewById(R.id.botaoFazerCadastro)
        fazercadastro.setOnClickListener {
            val intent = Intent(this, SignUp01::class.java)
            startActivity(intent)
        }
/*
        val continuarlogin: Button = findViewById(R.id.botaoContinuarLogin)
        continuarlogin.setOnClickListener {
            val usuarioEncontrado = listaLoginFake.find {
                it.name == nome && it.password == senha
            }
            if (usuarioEncontrado != null) {
                val intent = Intent(this, Logado::class.java)
                startActivity(intent)
            } else {
                showToast(this,"Usuário não encontrado")
            }
        }
        */
    }
}