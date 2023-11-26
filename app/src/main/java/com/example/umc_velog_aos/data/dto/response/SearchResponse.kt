package com.example.umc_velog_aos.data.dto.response

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("boardId") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("content") val content: String,
    @SerializedName("postImg") val postImg: String,
    @SerializedName("userName") val writerId: String,
    @SerializedName("commentsCount") val commentsCount: Int,
    @SerializedName("createdDate") val createdDate: String,
    @SerializedName("likeCount") val likeCount: Int,
)
