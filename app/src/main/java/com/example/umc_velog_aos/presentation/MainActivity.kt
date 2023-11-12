package com.example.umc_velog_aos.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.umc_velog_aos.presentation.login.LoginActivity
import com.example.umc_velog_aos.R
import com.example.umc_velog_aos.adapter.PostAdapter
import com.example.umc_velog_aos.databinding.ActivityMainBinding
import com.example.umc_velog_aos.dto.PostDTO
import com.example.umc_velog_aos.presentation.search.SearchFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val sfragmentManager = supportFragmentManager.beginTransaction()
    private val mainPostList = mutableListOf<PostDTO>()
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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
        mainPostList.add(post12)


        val postAdapter = PostAdapter(this, mainPostList)
        binding.mainRecycler.adapter = postAdapter
        val layoutManager = LinearLayoutManager(this)
        binding.mainRecycler.layoutManager = layoutManager

        btnListener()
    }
    private fun btnListener(){
        val fragmentSearch = SearchFragment()
        //검색 창 변경
        binding.btnMainSearch.setOnClickListener {
            sfragmentManager.replace(R.id.main_frame, fragmentSearch)
                .commitAllowingStateLoss()
            //만약에 이미 바뀌었으면 확인 후 새로고침 구현
        }
        //로그인 창 띄우기
        binding.btnMainLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            //애니메이션은 추후 업데이트
        }
        //창 리로드
        binding.ivVelogTitle.setOnClickListener {
            //어댑터 리로드 호출
        }
        //모드 변경은 지원 안 함
        binding.btnMainMode.setOnClickListener {
            Toast.makeText(this@MainActivity, "지원하지 않는 기능입니다.", Toast.LENGTH_SHORT).show()
        }
    }
//    private fun reloadPage(fragment: Fragment, fragmentManager: FragmentManager) {
//        val fragmentTransaction = fragmentManager.beginTransaction()
//        fragmentTransaction
//            .detach(fragment).attach(fragment)
//            .commitAllowingStateLoss()
//    }
}