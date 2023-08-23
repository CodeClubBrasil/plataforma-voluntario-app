package com.example.codeclubapp.src.classesModelos.ROOM_MODEL

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.codeclubapp.src.classesModelos.ROOM_MODEL.UserDaoImpl
import com.example.codeclubapp.src.classesModelos.ROOM_MODEL.UserRoom

class UsuarioRoomViewModel(application: Application) : AndroidViewModel(application) {
    suspend fun insertItem(userRoom: UserRoom){
        UserDaoImpl(getApplication()).insertNewItem(userRoom)
    }

    suspend fun getAllItems() : List<UserRoom>{
        return UserDaoImpl(getApplication()).getAllUsers()
    }

}