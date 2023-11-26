//package com.example.umc_velog_aos.repository
//
//import com.example.umc_velog_aos.data.dto.response.Post
//import com.example.umc_velog_aos.service.BoardService
//
//class PostRepository(private val boardService: BoardService) {
//    suspend fun getSearchBoards(): List<Post>? {
//        return try {
//            val response = boardService.getSearchBoards(requestBody)
//            if (response.isSuccessful) {
//                response.body()
//            } else {
//                null
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//            null
//        }
//    }
//}