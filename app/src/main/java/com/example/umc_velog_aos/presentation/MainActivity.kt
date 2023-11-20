package com.example.umc_velog_aos.presentation

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.umc_velog_aos.R
import com.example.umc_velog_aos.databinding.ActivityMainBinding
import com.example.umc_velog_aos.presentation.login.LoginActivity
import com.example.umc_velog_aos.presentation.search.SearchFragment
import com.example.umc_velog_aos.util.hideKeyboard
import com.google.android.material.tabs.TabLayout
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //초기 탭 화면 설정
        val initFrag = MainTrendFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_tab, initFrag)
            .commit()

        btnListener()
        changeTab()
    }
    private fun btnListener(){
        val sfragmentManager = supportFragmentManager.beginTransaction()
        val fragmentSearch = SearchFragment()
        //검색 창 변경
        binding.btnMainSearch.setOnClickListener {
            val fragment = supportFragmentManager.findFragmentByTag("search")
            //이미 열려 있으면 새로고침 될 수 있도록
            if (fragment!=null && fragment is SearchFragment) {
                sfragmentManager.detach(fragmentSearch).attach(fragmentSearch)
                    .commitAllowingStateLoss()
            }else{
                sfragmentManager.replace(R.id.main_frame, fragmentSearch, "search")
                    .commitAllowingStateLoss()
            }
        }
        //로그인 창 띄우기
        binding.btnMainLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            //애니메이션은 추후 업데이트
        }
        //창 리로드
        binding.ivVelogTitle.setOnClickListener {
            finish()
            overridePendingTransition(0, 0)
            val intent = intent
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
        //모드 변경은 지원 안 함
        binding.btnMainMode.setOnClickListener {
            Toast.makeText(this@MainActivity, "지원하지 않는 기능입니다.", Toast.LENGTH_SHORT).show()
        }
    }
    //트렌드 / 최신 탭 변경
    private fun changeTab() {
        binding.tabMain.addOnTabSelectedListener(object: TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val fragment = when (tab.position) {
                    0 -> MainTrendFragment()
                    1 -> MainLatestFragment()
                    else -> null
                }
                if (fragment != null) {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_tab, fragment)
                        .commit()
                }
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}
            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }
    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        val focusView = currentFocus
        if (focusView != null) {
            val rect = Rect()
            focusView.getGlobalVisibleRect(rect)
            val x = ev!!.x.toInt()
            val y = ev.y.toInt()
            if (!rect.contains(x, y)) {
                hideKeyboard(focusView)
            }
        }
        return super.dispatchTouchEvent(ev)
    }
}