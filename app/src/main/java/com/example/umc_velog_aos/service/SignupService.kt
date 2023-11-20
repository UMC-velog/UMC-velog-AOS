package com.example.umc_velog_aos.service

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignupService {

    @POST("/auth/register")
    fun postRegister(@Body requestBody: RequestBody): Call<Void>
}