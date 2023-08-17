package com.example.codeclubapp.src.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppRetrofit {
    private val retrofit = Retrofit.Builder()
        .baseUrl("inserir_URL_base")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val Service = retrofit.create(Service::class.java)

}