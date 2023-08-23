package com.example.codeclubapp.src.classesModelos.ROOM_MODEL

import android.content.Context

class UserDaoImpl(context: Context) : UserDAO {

    private val userDao = DatabaseManager.getDatabaseInstance(context).userDAO()
    override fun insertNewItem(userRoom: UserRoom) {
        userDao.insertNewItem(userRoom).also {
            println("Usuario inserido username: " + userRoom.username)
        }
    }

    override fun updateNewItem(userRoom: UserRoom) {
        TODO("Not yet implemented")
    }

    override fun deleteItem(userRoom: UserRoom) {
        TODO("Not yet implemented")
    }

    override fun getAllUsers(): List<UserRoom> {
        return userDao.getAllUsers()
    }
}