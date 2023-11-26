package com.example.umc_velog_aos.presentation.search

import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.umc_velog_aos.databinding.FragmentSearchBinding
import com.example.umc_velog_aos.data.dto.response.SearchResponse
import com.example.umc_velog_aos.presentation.adapter.SearchAdapter
import com.example.umc_velog_aos.service.ApiClient
import com.example.umc_velog_aos.service.BoardService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchFragment : Fragment() {
    private lateinit var binding: FragmentSearchBinding
    private val searchPostList = mutableListOf<SearchResponse>()
    private lateinit var postAdapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchBinding.inflate(layoutInflater, container, false)


        postAdapter = SearchAdapter(requireContext(), searchPostList)
        binding.searchRecycler.adapter = postAdapter
        val layoutManager = LinearLayoutManager(requireContext())
        binding.searchRecycler.layoutManager = layoutManager
        binding.tvResult.visibility = View.VISIBLE

        binding.etMainSearch.addTextChangedListener(object : TextWatcher {
            private val DELAY: Long = 300
            private var timer: CountDownTimer? = null

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                timer?.cancel() // 타이머 초기화
                timer = object : CountDownTimer(DELAY, DELAY) {
                    override fun onTick(millisUntilFinished: Long) {}

                    override fun onFinish() {
                        CoroutineScope(Dispatchers.Main).launch {
                            getSearchBoard(s.toString())
                        }
                    }
                }.start()
            }
        })
//        CoroutineScope(Dispatchers.Main).launch {
//            getSearchBoard("insta")
//        }
        return binding.root
    }

    private suspend fun getSearchBoard(keyword: String) {
        val apiService = ApiClient.getApiClient().create(BoardService::class.java)
        lifecycleScope.launch {
            try {
                val response = apiService.searchBoards(keyword)
                if (response == null) {

                } else {
                    response?.let {
                        searchPostList.clear()
                        searchPostList.addAll(it) // 받은 데이터를 리스트에 추가
                        postAdapter.notifyDataSetChanged() // 어댑터에 데이터가 변경되었음을 알림
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                Toast.makeText(requireContext(), "다시 시도해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }
}