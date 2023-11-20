package com.example.umc_velog_aos.data.dto.response

data class Post(
    val postImg: String,
    val id: Int,
    val title: String,
    val content: String,
    val writerId: String,
    val createdDate: String,
    val likeCount: Int,
)
