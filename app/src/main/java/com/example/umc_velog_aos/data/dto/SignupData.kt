package com.example.umc_velog_aos.data.dto

data class LoginModel (
    var userId: String? = null,
    var password: String? = null
)

data class RegisterModel (
    var userId: String? = null,
    var password: String? = null,
    var role: String? = null,
    var userName: String? = null
)