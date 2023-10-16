package com.example.codeclubapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.codeclubapp.databinding.ActivitySignUp01Binding
import com.example.codeclubapp.src.classesModelos.user.Address
import com.example.codeclubapp.src.classesModelos.user.AvaiableTime
import com.example.codeclubapp.src.classesModelos.user.Contacts
import com.example.codeclubapp.src.classesModelos.user.DAYS
import com.example.codeclubapp.src.classesModelos.user.Knowledges
import com.example.codeclubapp.src.classesModelos.user.UserCodeClub
import com.example.codeclubapp.src.ui.viewmodel.SignUpViewModel
import com.example.codeclubapp.src.utils.CC_Utils
import org.koin.androidx.viewmodel.ext.android.viewModel

private const val TAG = "SignUp01"

class SignUp01 : AppCompatActivity() {


    private val signUpViewModel: SignUpViewModel by viewModel()
    private lateinit var binding: ActivitySignUp01Binding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUp01Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //Declarando as variáveis de cada campo
        var name = ""
        var lastName = ""
        var city = ""
        var stateSelected = " "
        var neighborhood = ""
        var tels = ""
        var knowledges = ""
        var email = ""
        var password = ""

        //Lidando com o array de Estados
        val stateSpinner = binding.stateSpinner
        val estados = resources.getStringArray(R.array.siglasEstados)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, estados)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        stateSpinner.adapter = adapter

        //Definindo qual estado foi selecionado pelo usuário
        stateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val estadoSelecionado = estados[position]
                stateSelected = estadoSelecionado
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        //Botão CADASTRAR clicado
        binding.btnContinueToSignUp02.setOnClickListener {
            //Atualizando variáveis para o que foi digitado pelo usuário
            name = binding.editTextNome.text.toString()
            lastName = binding.editTextSobrenome.text.toString()
            city = binding.editTextCidade.text.toString()
            neighborhood = binding.editTextBairro.text.toString()
            tels = binding.editTextTelefone.text.toString()
            knowledges = binding.editTextKnowledge.text.toString()
            email = binding.editTextEmail.text.toString()
            password = binding.editTextSenha.text.toString()

            Log.i(TAG, " Salvando usuario de nome: $name $lastName e city: $city  e estado $stateSelected")

            //Cadastrar usuário se os formulários forem válidos
            if (validaFormulario(
                    name,
                    lastName,
                    password,
                    city,
                    stateSelected,
                    neighborhood,
                    tels,
                    email,
                    knowledges
                )
            ) {
                CC_Utils.showToast(this, "Usuario $name cadastrado com sucesso")

                saveUserOnDatabase(
                    name,
                    lastName,
                    password,
                    city,
                    stateSelected,
                    neighborhood,
                    tels,
                    email,
                    knowledges
                )
            }
        }

        val entrar = binding.botaoEntrar
        entrar.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
        }

    }

    private fun validaFormulario(
        name: String,
        lastName: String,
        password: String,
        city: String,
        state: String,
        neighborhood: String,
        tels: String,
        email: String,
        knowledges: String,
    ): Boolean {
        if (name.isBlank()) {
            CC_Utils.showToast(this@SignUp01, "O nome não pode estar em branco")
            return false
        } else if (lastName.isBlank()) {
            CC_Utils.showToast(this@SignUp01, "O sobrenome não pode estar em branco")
            return false
        } else if (city.isBlank()) {
            CC_Utils.showToast(this@SignUp01, "A cidade não pode estar em branco")
            return false
        } else if (state.isBlank()) {
            CC_Utils.showToast(this@SignUp01, "O Estado não pode estar em branco")
            return false
        } else if (neighborhood.isBlank()) {
            CC_Utils.showToast(this@SignUp01, "O Bairro não pode estar em branco")
            return false
        } else if (tels.isBlank()) {
            CC_Utils.showToast(this@SignUp01, "O Telefone não pode estar em branco")
            return false
        } else if (knowledges.isBlank()) {
            CC_Utils.showToast(this@SignUp01, "O Conhecimento não pode estar em branco")
            return false
        } else if (email.isBlank()) {
            CC_Utils.showToast(this@SignUp01, "O Email não pode estar em branco")
            return false
        } else if (password.isBlank()) {
            CC_Utils.showToast(this@SignUp01, "A senha não pode estar em branco")
            return false
        } else {
            Log.i(TAG, "validaFormulario:   Campos validados com sucesso")
            return true
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun saveUserOnDatabase(
        name: String,
        lastName: String,
        password: String,
        city: String,
        state: String,
        neighborhood: String,
        tels: String,
        email: String,
        knowledges: String,
    ) {
        val userAddress = Address(city, state, neighborhood)
        val userContacts = Contacts(tels, email)
        val avaiableTime = AvaiableTime(
            DAYS.MONDAY,
            System.currentTimeMillis(),
            System.currentTimeMillis()
        )
        //val knowLedges = Knowledges("KOTLIN")
        val user = UserCodeClub(
            name = name,
            lastName = lastName,
            password = password,
            address = userAddress,
            contacts = userContacts,
            avaiableTime = avaiableTime,
            knowledges = Knowledges(knowledges)
        )
        signUpViewModel.insertNewUser(user)
    }
}