package com.example.codeclubapp.src.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.codeclubapp.src.classesModelos.user.UserCodeClub
import com.example.codeclubapp.src.db.user.UserDaoImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SignUpViewModel(private val userDaoImpl: UserDaoImpl) : ViewModel() {

    fun insertNewUser(userCodeClub: UserCodeClub) {
        CoroutineScope(Dispatchers.IO).launch {
            userDaoImpl.insertNewUser(userCodeClub)
        }
    }
}