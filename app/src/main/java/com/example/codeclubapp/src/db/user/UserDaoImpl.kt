package com.example.codeclubapp.src.db.user

import android.util.Log
import com.example.codeclubapp.src.classesModelos.user.UserCodeClub
import com.example.codeclubapp.src.db.DatabaseManager
import com.example.codeclubapp.src.utils.CCUtils


private val INFO = "CC_INFO"

class UserDaoImpl(private val databaseManager: DatabaseManager) : UserCodeClubeDAO {
    private val userDAO = databaseManager.userDAO()
    override fun insertNewUser(userCodeClub: UserCodeClub) {
        val encodePassword = CCUtils.encodePassword(userCodeClub.getPassword())
        userCodeClub.setPassword(encodePassword)
        userDAO.insertNewUser(userCodeClub).also {
            Log.i(INFO, "usuário inserido com sucesso! ${userCodeClub.name} - ${userCodeClub.lastName}")
        }
    }
}