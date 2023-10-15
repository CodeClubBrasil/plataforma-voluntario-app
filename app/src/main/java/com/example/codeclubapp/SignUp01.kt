package com.example.codeclubapp

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
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
import com.example.codeclubapp.src.utils.CCUtils
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.math.sign

class SignUp01 : AppCompatActivity() {


    private val signUpViewModel: SignUpViewModel by viewModel()
    private lateinit var binding: ActivitySignUp01Binding
    private var byteArrayImg: ByteArray? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUp01Binding.inflate(layoutInflater)
        setContentView(binding.root)


        val stateSpinner: Spinner = findViewById(R.id.stateSpinner)
        val estados = resources.getStringArray(R.array.siglasEstados)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, estados)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        stateSpinner.adapter = adapter


        binding.btnContinueToSignUp02.setOnClickListener {
            val name = binding.editTextNome.text.toString()
            val lastName = binding.editTextSobrenome.text.toString()
            val password = binding.editTextSenha.text.toString()
            val city = binding.editTextCidade.text.toString()
            val neighborhood = binding.editTextBairro.text.toString()
            val state = "buscar estado do Spinner"
            val tels = binding.editTextTelefone.text.toString()
            val email = binding.editTextEmail.text.toString()
            val knowledges = binding.editTextKnowledge.text.toString()

            Log.i(TAG, " Salvando usuario de nome: $name $lastName e city: $city ")
            CCUtils.showToast(this, "Usuario $name cadastrado com sucesso")
            saveUserOnDatabase(
                name,
                lastName,
                password,
                city,
                state,
                neighborhood,
                tels,
                email,
                knowledges
            )
        }

        val entrar: Button = findViewById(R.id.botaoEntrar)
        entrar.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
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
            0,
            name,
            lastName,
            password,
            userAddress,
            userContacts,
            avaiableTime,
            Knowledges(knowledges)
        )
        signUpViewModel.insertNewUser(user)
    }




}