package com.example.codeclubapp.src.retrofit

import com.example.codeclubapp.src.retrofit.dto.CodeClubs
import com.example.codeclubapp.src.retrofit.dto.user.CreateUser
import com.example.codeclubapp.src.retrofit.dto.user.UserOutput
import com.example.codeclubapp.src.retrofit.user.UserAPI
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface Service {

    @GET("/club")
    suspend fun getCodeClub(
        @Query("name") name:String,
        @Query("state") state: String,
        @Query("city") city:String,
        @Query("neighborhood")neighborhood:String
    ): CodeClubs

    @GET("/user/login")
    suspend fun login()

    @POST("user")
    suspend fun createUser(@Body createUser: UserAPI): Response<UserOutput>

    @Multipart
    @POST("user/send-document")
    suspend fun sendDocument(
        @Part("documentType") documentType: String,
        @Part fileName: MultipartBody.Part
    )

}