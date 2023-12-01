package com.example.codeclubapp.src.retrofit.dto.user

import com.example.codeclubapp.src.retrofit.user.AvailableTime

data class UserOutput(
    val active: Boolean,
    val available_time: List<AvailableTime>,
    val city: String,
    val created_at: String,
    val email: String,
    val id: String,
    val know_ledges: List<String>,
    val last_name: String,
    val name: String,
    val neighborhood: String,
    val password: String,
    val state: String,
    val telephone: List<String>,
    val updated_at: Any,
    val user_name: String
)