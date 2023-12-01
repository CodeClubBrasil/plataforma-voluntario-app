package com.example.codeclubapp.src.retrofit.user

data class UserAPI(
    val active: Boolean,
    val available_time: List<AvailableTime>,
    val city: String,
    val created_at: String,
    val email: String,
    val knowledges: List<String>,
    val last_name: String,
    val name: String,
    val neighborhood: String,
    val password: String,
    val state: String,
    val telephone: List<String>,
    val username: String
)