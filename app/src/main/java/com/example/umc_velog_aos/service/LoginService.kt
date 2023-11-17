package com.example.umc_velog_aos.service

import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginService {

    @POST("/auth/login")
    fun postLogin(
        @Header("userId") username: String,
        @Header("password") password: String,
        @Body requestBody: RequestBody)

    @POST("/auth/logout")
    fun postLogout(
        @Header("accessToken") accessToken: String,
        @Body requestBody: RequestBody)
}