package com.example.umc_velog_aos.service

import com.example.umc_velog_aos.dto.Post
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ServiceAPI {
    @POST("/sign-up")
    fun postSignup(@Body requestBody: RequestBody)
}