package com.example.umc_velog_aos.data.dto.response

import com.google.gson.annotations.SerializedName

data class JWTTokenResponse(
    @SerializedName("accessToken") val accessToken: String,
    @SerializedName("refreshToken") val refreshToken: String
)
