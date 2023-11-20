package com.example.umc_velog_aos.service

import com.example.umc_velog_aos.data.dto.response.Post
import retrofit2.Call
import retrofit2.http.GET

interface BoardService {
    @GET("/boards")
    fun getBoards(): Call<List<Post>>

    @GET("/boards/search")
    fun getSearchBoards(): Call<List<Post>>
}