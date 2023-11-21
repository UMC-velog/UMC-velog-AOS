package com.example.umc_velog_aos.data.dto.response

import com.google.gson.annotations.SerializedName

data class Post(
    @SerializedName("postImg") val postImg: String?,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("writerId") val writerId: String,
    @SerializedName("createdDate") val createdDate: String,
    @SerializedName("likeCount") val likeCount: Int,
    @SerializedName("comments") val comments: List<Comment>
)

data class Comment(
    val id: Int
)
