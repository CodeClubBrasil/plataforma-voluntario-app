package com.example.codeclubapp.src.retrofit.dto

data class CodeClubs (
    var address         : Address?                  = Address(),
    var name            : String?                   = null,
    var responsibleName : String?                   = null,
    var contacts        : ArrayList<String>         = arrayListOf(),
    var availableTimes  : ArrayList<AvailableTimes> = arrayListOf()
)