package com.example.umc_velog_aos.service

import com.example.umc_velog_aos.presentation.signup.LoginModel
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ServiceAPI {
    @POST("/api/auth/login")
    @Headers("accept: application/json",
        "content-type: application/json")
    fun post_login(
        @Body jsonparams: LoginModel
    )


//    @POST("/api/auth/register")


//    @GET("/api/auth/info")


}