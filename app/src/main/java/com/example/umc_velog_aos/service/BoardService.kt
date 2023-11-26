package com.example.umc_velog_aos.service

import com.example.umc_velog_aos.data.dto.response.Post
import com.example.umc_velog_aos.data.dto.response.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface BoardService {
    @GET("/boards")
    fun getBoards(): Call<List<Post>>

    @GET("/boards/search")
    suspend fun searchBoards(@Query("keyword") keyword: String): List<SearchResponse>

//    @POST("/boards/write-form")
//    suspend fun postWrite(
//        @Header("Authorization")
//    )
}