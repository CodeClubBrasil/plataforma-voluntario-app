package com.example.codeclubapp.src.retrofit.dto

data class AvailableTimes (
    var weekDays : ArrayList<String> = arrayListOf(),
    var time     : Time?             = Time()

)
