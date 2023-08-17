package com.example.codeclubapp.src.retrofit

import android.util.Log
import com.example.codeclubapp.src.retrofit.modelosResposta.CodeClubs
import java.lang.Exception

class WebClient {
    val Service = AppRetrofit().Service

    suspend fun buscaCodeClubs() : CodeClubs? {
        return try {
            Service.getCodeClub()
        } catch (e : Exception){
            Log.e("WebClient", "A função buscaCodeClubs: retornou um erro ", e )
            return  null
        }
    }
}
