package com.example.umc_velog_aos.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_velog_aos.adapter.PostAdapter
import com.example.umc_velog_aos.databinding.FragmentSearchBinding
import com.example.umc_velog_aos.dto.Post

class SearchFragment: Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val mainPostList = mutableListOf<Post>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)



        val postAdapter = PostAdapter(requireContext(), mainPostList)
        binding.searchRecycler.adapter = postAdapter
        val layoutManager = LinearLayoutManager(requireContext())
        binding.searchRecycler.layoutManager = layoutManager

        binding.tvResult.visibility = View.VISIBLE



        //1. 초기화면은 빈 화면
        //2. 검색어 입력 시 검색 창 옅어짐 -> selector로 배경 변경
        //3. 입력 시 API를 통해 데이터 불러옴
        //4. 클릭 시 이미지, 제목 본문은 post fragment로, 유저부분은 profile로 이동





        return binding.root
    }
}