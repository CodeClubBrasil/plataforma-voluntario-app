package com.example.codeclubapp.src.retrofit.modelosResposta

import com.google.gson.annotations.SerializedName

data class CodeClubs (
    var address         : Address?                  = Address(),
    var name            : String?                   = null,
    var responsibleName : String?                   = null,
    var contacts        : ArrayList<String>         = arrayListOf(),
    var availableTimes  : ArrayList<AvailableTimes> = arrayListOf()
)