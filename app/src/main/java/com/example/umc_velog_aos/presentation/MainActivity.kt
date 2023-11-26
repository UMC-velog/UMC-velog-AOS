package com.example.umc_velog_aos.presentation

import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.PopupWindow
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.umc_velog_aos.R
import com.example.umc_velog_aos.application.ApplicationClass
import com.example.umc_velog_aos.data.module.DataStoreModule
import com.example.umc_velog_aos.databinding.ActivityMainBinding
import com.example.umc_velog_aos.presentation.login.LoginActivity
import com.example.umc_velog_aos.presentation.post.PostWriteActivity
import com.example.umc_velog_aos.presentation.profile.ProfileFragment
import com.example.umc_velog_aos.presentation.search.SearchFragment
import com.example.umc_velog_aos.util.hideKeyboard
import com.google.android.material.tabs.TabLayout
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dataStoreModule: DataStoreModule
    private var isPopupVisible = false
    private var popupWindow: PopupWindow? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        dataStoreModule = DataStoreModule(context = this)

        //초기 탭 화면 설정
        val initFrag = MainTrendFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame_tab, initFrag)
            .commit()
        btnListener()
        popupMenu()
        changeTab()
       // init()
    }
    override fun onDestroy() {
        super.onDestroy()
        popupWindow?.dismiss()
    }

    override fun onResume() {
        super.onResume()
        init()
    }

    private fun btnListener() {
        val sfragmentManager = supportFragmentManager.beginTransaction()
        val fragmentSearch = SearchFragment()
        //검색 창 변경
        binding.btnMainSearch.setOnClickListener {
            val fragment = supportFragmentManager.findFragmentByTag("search")
            //이미 열려 있으면 새로고침 될 수 있도록
            if (fragment != null && fragment is SearchFragment) {
                sfragmentManager.detach(fragmentSearch).attach(fragmentSearch)
                    .commitAllowingStateLoss()
            } else {
                sfragmentManager.replace(R.id.main_frame, fragmentSearch, "search")
                    .commitAllowingStateLoss()
            }
        }
        //로그인 창 띄우기
        binding.btnMainLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        //창 리로드
        binding.ivVelogTitle.setOnClickListener {
            finish()
            overridePendingTransition(0, 0)
            val intent = intent
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
        binding.btnMainMode.setOnClickListener {
            Toast.makeText(this@MainActivity, "지원하지 않는 기능입니다.", Toast.LENGTH_SHORT).show()
        }
    }
    private fun popupMenu() {
        //trending 팝업
        binding.popupMain.setOnClickListener {
            val popupView = LayoutInflater.from(this).inflate(R.layout.custom_trend_popup, null)
            val buttonIds = arrayOf(R.id.btn_today, R.id.btn_week, R.id.btn_month, R.id.btn_year)
            val buttons = buttonIds.map { popupView.findViewById<Button>(it) }
            updatePopup(binding.tvPopup.text.toString(), buttons)
            val popupWindow = PopupWindow(popupView, 192, 169, true)
            popupWindow.showAsDropDown(binding.popupMain, -108, 16)
            buttons.forEach { button ->
                button.setOnClickListener {
                    binding.tvPopup.text = button.text
                    popupWindow.dismiss()
                }
            }
            popupWindow.isOutsideTouchable = true
            popupWindow.setOnDismissListener {
                isPopupVisible = false
            }
            isPopupVisible = true
        }
        //extra
        binding.popupExtra.setOnClickListener {
            val popupView = LayoutInflater.from(this).inflate(R.layout.custom_main_popup, null)
            showPopup(popupView, binding.popupExtra, 192, 313, -168, 16)
        }
        //login
        binding.popupProfile.setOnClickListener {
            val popupView = LayoutInflater.from(this).inflate(R.layout.custom_profile_popup, null)
            showPopup(popupView, binding.popupProfile, 192, 288, -131, 16)
            val btnLogout = popupView.findViewById<Button>(R.id.btn_logout)
            val btnMyProfile = popupView.findViewById<Button>(R.id.btn_my_velog)
            val btnNewPost = popupView.findViewById<Button>(R.id.btn_new_post)

            btnLogout.setOnClickListener {
                lifecycleScope.launch {
                    ApplicationClass.getInstance().getDataStore().removeToken("ACCESS_TOKEN")
                    ApplicationClass.getInstance().getDataStore().removeToken("REFRESH_TOKEN")
                    finish()
                    overridePendingTransition(0, 0)
                    val intent = intent
                    startActivity(intent)
                    overridePendingTransition(0, 0)
                }
            }
            btnMyProfile.setOnClickListener {
                val profileFragment = ProfileFragment()
                val bt = supportFragmentManager.beginTransaction()
                bt.replace(R.id.main_frame, profileFragment)
                    .commitAllowingStateLoss()
                popupWindow?.dismiss()
                //아이콘이랑 아이디 변경 view gone
            }
            btnNewPost.setOnClickListener {
                val intent = Intent(this, PostWriteActivity::class.java)
                startActivity(intent)
                popupWindow?.dismiss()
            }
        }
    }
    private fun showPopup(popupView: View, anchorView: View, width: Int, height: Int, offsetX: Int, offsetY: Int) {
        popupWindow = PopupWindow(popupView, width, height, true)
        popupWindow!!.showAsDropDown(anchorView, offsetX, offsetY)
        popupWindow!!.isOutsideTouchable = true
        popupWindow!!.setOnDismissListener {
            isPopupVisible = false
        }
        isPopupVisible = true
    }
    private fun updatePopup(currentText: String, buttons: List<Button>) {
        buttons.forEach { button ->
            if (button.text == currentText) {
                button.setTextColor(ContextCompat.getColor(this, R.color.velog_green))
            } else {
                button.setTextColor(ContextCompat.getColor(this, R.color.background_black))
            }
        }
    }
    //트렌드 / 최신 탭 변경
    private fun changeTab() {
        binding.tabMain.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val fragment = when (tab.position) {
                    0 -> {
                        binding.popupMain.visibility = View.VISIBLE
                        MainTrendFragment()
                    }
                    1 -> {
                        binding.popupMain.visibility = View.INVISIBLE
                        MainLatestFragment()
                    }
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
    private fun init() {
        CoroutineScope(Dispatchers.Main).launch {
            delay(500L)
            if (ApplicationClass.getInstance().getDataStore().hasToken()) {
                println(ApplicationClass.getInstance().getDataStore().accessToken.first())
                binding.popupProfile.visibility = View.VISIBLE
                binding.btnMainLogin.visibility = View.GONE
            } else {
                binding.popupProfile.visibility = View.GONE
                binding.btnMainLogin.visibility = View.VISIBLE
            }
        }
    }
}