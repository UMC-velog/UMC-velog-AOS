package com.example.umc_velog_aos.service

import com.example.umc_velog_aos.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = BuildConfig.BASE_URL
    private var builder = OkHttpClient().newBuilder()
    private var okHttpClient = builder.build()

    private val client = Retrofit.Builder()
        .baseUrl(BASE_URL).client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getApiClient() : Retrofit {
        return client
    }
}