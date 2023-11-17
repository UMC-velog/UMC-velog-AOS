package com.example.umc_velog_aos.dto.request

data class Signup(
    val userId: String,
    val userName: String,
    val password: String,
    val email: String,
    val role: String
)
