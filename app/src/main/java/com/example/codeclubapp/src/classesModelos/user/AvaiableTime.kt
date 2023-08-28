package com.example.codeclubapp.src.classesModelos.user

import com.example.codeclubapp.src.retrofit.modelosResposta.AvailableTimes
import java.time.LocalDateTime

data class AvaiableTime(
    var weekDays: DAYS,
    var timeStart: Long,
    var timeEnd: Long
)