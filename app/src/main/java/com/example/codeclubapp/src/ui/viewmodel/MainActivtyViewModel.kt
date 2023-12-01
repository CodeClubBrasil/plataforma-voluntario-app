package com.example.codeclubapp.src.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.codeclubapp.src.db.user.UserDaoImpl
import com.example.codeclubapp.src.retrofit.AppRetrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val userDaoImpl: UserDaoImpl,
    private val appRetrofit: AppRetrofit
) : ViewModel() {

    private var _userLogged = MutableLiveData<Boolean>()
    val userLogged: LiveData<Boolean> = _userLogged

    fun persistUserIfConnectiveIsOkAndHasToBePersisted() {
        CoroutineScope(Dispatchers.IO).launch {
            val isNotEmpty = userDaoImpl.getAll().isNotEmpty()
            if (isNotEmpty) {
                userDaoImpl.getAll().forEach { roomUser ->
                    if (roomUser.needToBePersisted) {
                        appRetrofit.Service.createUser(roomUser.toCreateUser())
                        roomUser.needToBePersisted = false
                        roomUser.logged = true
                        userDaoImpl.updateRoomUser(roomUser)
                        _userLogged.postValue(true)
                    }
                }
            }
        }
    }

    fun verifyIfUserIsLogged() {
        CoroutineScope(Dispatchers.IO).launch {
            userDaoImpl.getAll().forEach {roomUser ->
                if (roomUser.logged) _userLogged.postValue(true)
            }
        }
    }
}