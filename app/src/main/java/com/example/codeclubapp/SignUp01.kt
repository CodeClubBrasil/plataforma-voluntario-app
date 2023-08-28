package com.example.codeclubapp

import android.content.Intent
import android.os.Build
import android.os.Bundle
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
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUp01 : AppCompatActivity() {


    private val signUpViewModel: SignUpViewModel by viewModel()
    private lateinit var binding: ActivitySignUp01Binding

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
            saveUserOnDatabase()
        }

        val entrar: Button = findViewById(R.id.botaoEntrar)
        entrar.setOnClickListener {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun saveUserOnDatabase() {
        val userAddress = Address("castanhal", "PA", "santa lidia")
        val userContacts = Contacts("9196765641", "emailx@gmail.com")
        val avaiableTime = AvaiableTime(
            DAYS.MONDAY,
            System.currentTimeMillis(),
            System.currentTimeMillis()
        )
        val knowLedges = Knowledges("KOTLIN")
        val user = UserCodeClub(
            0,
            "VALTER",
            "GABRIEL",
            "1234",
            userAddress,
            userContacts,
            avaiableTime,
            knowLedges
        )
        signUpViewModel.insertNewUser(user)
    }
}