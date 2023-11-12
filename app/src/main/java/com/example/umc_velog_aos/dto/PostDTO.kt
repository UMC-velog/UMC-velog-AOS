package com.example.umc_velog_aos.dto

data class PostDTO (
    //val imageUrl: String,
    val title: String,
    val body: String,
    val date: String,
    val user: String,
    val likes: Int
)