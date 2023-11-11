package com.example.umc_velog_aos.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.umc_velog_aos.presentation.login.LoginActivity
import com.example.umc_velog_aos.R
import com.example.umc_velog_aos.databinding.ActivityMainBinding
import com.example.umc_velog_aos.presentation.search.SearchFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val sfragmentManager = supportFragmentManager.beginTransaction()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        btnListener()
    }
    private fun btnListener(){
        val fragmentSearch = SearchFragment()
        //검색 창 변경
        binding.btnMainSearch.setOnClickListener {
            sfragmentManager.replace(R.id.main_frame, fragmentSearch)
                .commit()
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