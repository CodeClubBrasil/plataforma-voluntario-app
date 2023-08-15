package com.example.codeclubapp.src.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.codeclubapp.src.data.IDadosImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(private val dados: IDadosImpl) : ViewModel() {

    val texto = MutableLiveData<String>()

    fun mostrarTexto() {
        viewModelScope.launch(Dispatchers.IO) {
            texto.value = dados.getData().toString()
        }
    }
}