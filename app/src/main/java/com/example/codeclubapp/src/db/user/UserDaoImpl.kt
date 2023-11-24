package com.example.codeclubapp.src.db.user

import android.util.Log
import com.example.codeclubapp.src.db.entities.RoomUser
import com.example.codeclubapp.src.db.DatabaseManager
import com.example.codeclubapp.src.utils.CCUtils


private val INFO = "CC_INFO"

class UserDaoImpl(private val databaseManager: DatabaseManager) : UserCodeClubeDAO {
    private val userDAO = databaseManager.userDAO()
    override fun insertNewUser(roomUser: RoomUser) {
        val encodePassword = CCUtils.encodePassword(roomUser.password)
        roomUser.password = encodePassword ?: ""
        userDAO.insertNewUser(roomUser).also {
            Log.i(INFO, "usuário inserido com sucesso! ${roomUser.name} - ${roomUser.lastname}")
        }
    }

    override fun getUserByUsername(username: String): RoomUser {
        TODO("Not yet implemented")
    }

    override fun deleteUserByNickname(username: String) {
        TODO("Not yet implemented")
    }
}