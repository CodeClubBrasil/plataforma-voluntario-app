package com.example.codeclubapp.src.retrofit

import android.util.Log
import com.example.codeclubapp.src.retrofit.dto.CodeClubs
import com.example.codeclubapp.src.retrofit.dto.user.CreateUser
import com.example.codeclubapp.src.retrofit.dto.user.UserOutput
import com.example.codeclubapp.src.retrofit.user.UserAPI
import java.lang.Exception

class WebClient {
    val Service = AppRetrofit().Service

    suspend fun buscaCodeClubs(): CodeClubs? {
        return try {
            Service.getCodeClub()
        } catch (e: Exception) {
            Log.e("WebClient", "A função buscaCodeClubs: retornou um erro ", e)
            return null
        }
    }

    suspend fun insertUser(createUser: UserAPI): Pair<Boolean, UserOutput?> {
        val user = Service.createUser(createUser)
        return Pair(user.isSuccessful, user.body())
    }
}
