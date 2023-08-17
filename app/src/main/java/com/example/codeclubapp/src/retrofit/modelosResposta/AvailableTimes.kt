package com.example.codeclubapp.src.retrofit.modelosResposta

data class AvailableTimes (
    var weekDays : ArrayList<String> = arrayListOf(),
    var time     : Time?             = Time()

)
