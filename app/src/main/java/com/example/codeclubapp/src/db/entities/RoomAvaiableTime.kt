package com.example.codeclubapp.src.db.entities

import com.example.codeclubapp.src.retrofit.user.AvailableTime

data class RoomAvaiableTime(
    var time_end: String,
    var time_start: String,
    var week_day: String
){
    fun toAvailableTime() : AvailableTime {
        return AvailableTime(this.time_end,this.time_start,this.week_day)
    }
}