package com.example.codeclubapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.codeclubapp.databinding.ActivityCadastro2Binding
import com.example.codeclubapp.src.ui.viewmodel.SignUpViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class SingUp02 : AppCompatActivity() {

    private lateinit var binding: ActivityCadastro2Binding
    private val signUpViewModel: SignUpViewModel by viewModel()
    private var byteArrayImg: ByteArray? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastro2Binding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}