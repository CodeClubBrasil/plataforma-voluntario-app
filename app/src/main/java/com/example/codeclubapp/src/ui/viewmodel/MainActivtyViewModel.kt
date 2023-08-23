package com.example.codeclubapp.src.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    val texto = MutableLiveData<String>()

    fun mostrarTexto() {
        viewModelScope.launch(Dispatchers.Main) {
            texto.value = "Teste"
        }
    }
}