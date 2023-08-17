package com.example.codeclubapp.src.classesModelos.user

import com.example.codeclubapp.src.retrofit.modelosResposta.AvailableTimes

data class User(
    var lastName: String? = null,
    var password: String? = null,
    var city: String? = null,
    var name: String? = null,
    var id: Int? = null,
    var state: String? = null,
    var neighborhood: String? = null,
    var tels: ArrayList<String> = arrayListOf(),
    var email: String? = null,
    var knowledges: ArrayList<String> = arrayListOf(),
    var availableTimes: ArrayList<AvailableTimes> = arrayListOf()
)