package com.example.umc_velog_aos.service

import com.example.umc_velog_aos.dto.Post
import retrofit2.Call
import retrofit2.http.GET

interface BoardService {
    @GET("/board")
    fun getBoards(): Call<List<Post>>
}