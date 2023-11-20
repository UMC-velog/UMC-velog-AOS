package com.example.umc_velog_aos.data.dto.request

data class SignupRequest(
    val userId: String,
    val userName: String,
    val password: String,
    val email: String,
    val role: String
)
