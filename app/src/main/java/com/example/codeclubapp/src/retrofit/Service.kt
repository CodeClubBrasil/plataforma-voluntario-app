package com.example.codeclubapp.src.retrofit

import com.example.codeclubapp.src.retrofit.dto.CodeClubs
import com.example.codeclubapp.src.retrofit.dto.user.CreateUser
import com.example.codeclubapp.src.retrofit.dto.user.UserOutput
import com.example.codeclubapp.src.retrofit.user.UserAPI
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Service {

    @GET("/club")
    suspend fun getCodeClub(): CodeClubs

    @GET("/user/login")
    suspend fun login()

    @POST("user")
    suspend fun createUser(@Body createUser: UserAPI): Response<UserOutput>

}