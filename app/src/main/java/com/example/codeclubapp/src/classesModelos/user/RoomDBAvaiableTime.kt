package com.example.codeclubapp.src.classesModelos.user

import com.example.codeclubapp.src.retrofit.dto.DAYS

data class RoomDBAvaiableTime(
    var weekDays: DAYS,
    var timeStart: Long,
    var timeEnd: Long
)