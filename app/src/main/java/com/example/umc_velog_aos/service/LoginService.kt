package com.example.umc_velog_aos.service

import com.example.umc_velog_aos.data.dto.response.JWTTokenResponse
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginService {

    @POST("/auth/login")
    fun postLogin(
        @Body requestBody: RequestBody): Call<JWTTokenResponse>

    @POST("/auth/logout")
    fun postLogout(
        @Header("accessToken") accessToken: String,
        @Body requestBody: RequestBody)
}