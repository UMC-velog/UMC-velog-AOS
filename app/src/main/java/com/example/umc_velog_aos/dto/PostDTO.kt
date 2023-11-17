package com.example.umc_velog_aos.dto

import java.util.Date

data class Post(
    val postImg: String,
    val id: Int,
    val title: String,
    val content: String,
    val writer: Writer,
    val createdDate: String,
    val likeCount: Int,
)

data class Writer(
    val id: Int,
    val username: String,
    val password: String,
    val email: String,
    val createDate: String,
)