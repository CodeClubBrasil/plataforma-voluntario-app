package com.example.codeclubapp.src.retrofit

import com.example.codeclubapp.src.retrofit.modelosResposta.CodeClubs
import retrofit2.http.GET

interface Service {

    @GET("/club")
    suspend fun getCodeClub() : CodeClubs

    @GET("/user/login")
    suspend fun login()
}