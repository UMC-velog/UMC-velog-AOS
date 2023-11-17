package com.example.umc_velog_aos.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_velog_aos.adapter.PostAdapter
import com.example.umc_velog_aos.databinding.FragmentMainTrendBinding
import com.example.umc_velog_aos.dto.Post
import com.example.umc_velog_aos.service.ApiClient
import com.example.umc_velog_aos.service.BoardService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainTrendFragment: Fragment() {
    private lateinit var binding: FragmentMainTrendBinding
    private val mainPostList = mutableListOf<Post>()
    private lateinit var postAdapter: PostAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainTrendBinding.inflate(inflater, container, false)

        postAdapter = PostAdapter(requireContext(), mainPostList)
        binding.trendRecycler.adapter = postAdapter
        val layoutManager = LinearLayoutManager(requireContext())
        binding.trendRecycler.layoutManager = layoutManager

        getBoard()
        return binding.root
    }

    private fun getBoard() {
        val apiService = ApiClient.getApiClient().create(BoardService::class.java)
        val call: Call<List<Post>> = apiService.getBoards()

        call.enqueue(object: Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    val posts: List<Post>? = response.body()
                    println( response.body())
                    posts?.let {
                        mainPostList.addAll(it) // 받은 데이터를 리스트에 추가
                        postAdapter.notifyDataSetChanged() // 어댑터에 데이터가 변경되었음을 알림
                    }
                    ///여기
                } else {
                    println("HTTP 오류: ${response.code()}")
                }
            }
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}