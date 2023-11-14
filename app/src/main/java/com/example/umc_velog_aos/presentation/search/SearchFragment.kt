package com.example.umc_velog_aos.presentation.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_velog_aos.adapter.PostAdapter
import com.example.umc_velog_aos.databinding.FragmentSearchBinding
import com.example.umc_velog_aos.dto.PostDTO

class SearchFragment: Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val mainPostList = mutableListOf<PostDTO>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)

        val post1 = PostDTO("제목1", "본문1", "2023년 11월 12일", "유저1", 10)
        val post2 = PostDTO("제목2", "본문2", "2023년 11월 13일", "유저2", 15)
        val post3 = PostDTO("제목3", "본문3", "2023년 11월 14일", "유저3", 20)
        val post4 = PostDTO("제목1", "본문1", "2023년 11월 12일", "유저1", 10)
        val post5 = PostDTO("제목2", "본문2", "2023년 11월 13일", "유저2", 15)
        val post6 = PostDTO("제목3", "본문3", "2023년 11월 14일", "유저3", 20)
        val post7 = PostDTO("제목1", "본문1", "2023년 11월 12일", "유저1", 10)
        val post8 = PostDTO("제목2", "본문2", "2023년 11월 13일", "유저2", 15)
        val post9 = PostDTO("제목3", "본문3", "2023년 11월 14일", "유저3", 20)
        val post10 = PostDTO("제목1", "본문1", "2023년 11월 12일", "유저1", 10)
        val post11 = PostDTO("제목2", "본문2", "2023년 11월 13일", "유저2", 15)
        val post12 = PostDTO("제목3", "본문3", "2023년 11월 14일", "유저3", 20)

        mainPostList.add(post1)
        mainPostList.add(post2)
        mainPostList.add(post3)
        mainPostList.add(post4)
        mainPostList.add(post5)
        mainPostList.add(post6)
        mainPostList.add(post7)
        mainPostList.add(post8)
        mainPostList.add(post9)
        mainPostList.add(post10)
        mainPostList.add(post11)

        val postAdapter = PostAdapter(requireContext(), mainPostList)
        binding.searchRecycler.adapter = postAdapter
        val layoutManager = LinearLayoutManager(requireContext())
        binding.searchRecycler.layoutManager = layoutManager

        binding.tvResult.visibility = View.VISIBLE

        return binding.root
    }
}