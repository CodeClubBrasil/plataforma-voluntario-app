package com.example.codeclubapp.src.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppRetrofit {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://plataforma-voluntario-api.vercel.app/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val Service = retrofit.create(Service::class.java)
}