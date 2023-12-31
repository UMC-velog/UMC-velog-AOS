package com.example.umc_velog_aos.presentation.login

import android.app.Activity
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.umc_velog_aos.R
import com.example.umc_velog_aos.application.ApplicationClass
import com.example.umc_velog_aos.data.dto.request.LoginRequest
import com.example.umc_velog_aos.data.dto.response.JWTTokenResponse
import com.example.umc_velog_aos.databinding.ActivityLoginBinding
import com.example.umc_velog_aos.presentation.MainActivity
import com.example.umc_velog_aos.presentation.signup.SignupFragment
import com.example.umc_velog_aos.service.ApiClient
import com.example.umc_velog_aos.service.LoginService
import com.example.umc_velog_aos.util.hideKeyboard
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var textState = true

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        btnListener()
        //emailWatcher(binding.etLogin)
    }
    private fun btnListener() {
        //로그인 버튼
        binding.btnLogin.setOnClickListener {
            //로그인 상태
            if (textState) {
                postLogin(binding.etLogin.text.toString(), binding.etPassword.text.toString())
                finish()
            } else { //회원가입 상태
                //작성한 이메일 정보
                val bundle = Bundle()
                val email = binding.etLogin.text.toString()
                bundle.putString("email", email)
                val fragmentSignup = SignupFragment()
                fragmentSignup.arguments = bundle
                val fragmentManager = supportFragmentManager.beginTransaction()

                //binding.tvEmailSuccess.text = resources.getText(R.string.sign_up_link)
                fragmentManager
                    .add(R.id.frame_login, fragmentSignup)
                    .commit()
            }
            //비밀번호 인증 방식으로 변경
            //binding.linearEmailBlock.visibility = View.GONE
            //binding.linearEmailBlockSuccess.visibility = View.VISIBLE
        }
        //닫기 버튼
        binding.btnLoginClose.setOnClickListener {
            finish()
        }
        //회원가입 버튼
        binding.tvSignup.setOnClickListener {
            if (textState) {
                binding.tvLogin.text = resources.getText(R.string.sign_up)
                binding.tvEmailLogin.text = resources.getText(R.string.sign_up_email)
                binding.btnLogin.text = resources.getText(R.string.sign_up)
                binding.tvSocialLogin.text = resources.getText(R.string.sign_up_social)
                binding.tvSignupNotyet.text = resources.getText(R.string.sign_up_already)
                binding.tvSignup.text = resources.getText(R.string.sign_in_u)
                binding.etPassword.visibility = View.GONE
                //binding.tvEmailSuccess.text = resources.getText(R.string.sign_up_link)
            } else {
                binding.tvLogin.text = resources.getText(R.string.sign_in)
                binding.tvEmailLogin.text = resources.getText(R.string.sign_in_email)
                binding.btnLogin.text = resources.getText(R.string.sign_in)
                binding.tvSocialLogin.text = resources.getText(R.string.sign_in_social)
                binding.tvSignupNotyet.text = resources.getText(R.string.sign_in_notyet)
                binding.tvSignup.text = resources.getText(R.string.sign_up_u)
                binding.etPassword.visibility = View.VISIBLE
                //binding.tvEmailSuccess.text = resources.getText(R.string.sign_in_link)
            }
            textState = !textState
        }
        //소셜 로그인 - 추후 구현
        binding.ibtnGithub.setOnClickListener {
            Toast.makeText(this@LoginActivity, "지원하지 않는 기능입니다.", Toast.LENGTH_SHORT).show()
        }
        binding.ibtnGoogle.setOnClickListener {
            Toast.makeText(this@LoginActivity, "지원하지 않는 기능입니다.", Toast.LENGTH_SHORT).show()
        }
        binding.ibtnFacebook.setOnClickListener {
            Toast.makeText(this@LoginActivity, "지원하지 않는 기능입니다.", Toast.LENGTH_SHORT).show()
        }
    }

    //키보드 밖 터치 시, 키보드 내림
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

    private fun emailWatcher(et: EditText) {
        et.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val email = et.text.toString()
                val isValidEmail = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
                binding.btnLogin.isEnabled = isValidEmail
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun postLogin(id: String, password: String) {
        val apiService = ApiClient.getApiClient().create(LoginService::class.java)
        val loginRequest = LoginRequest(id, password)
        val requestBody = GsonBuilder()
            .serializeNulls().create()
            .toJson(loginRequest)
            .toRequestBody("application/json".toMediaTypeOrNull())
        apiService.postLogin(requestBody).enqueue(object: Callback<JWTTokenResponse> {
            override fun onResponse(call: Call<JWTTokenResponse>, response: Response<JWTTokenResponse>) {
                if (response.isSuccessful) {
                    val jwtTokenResponse = response.body()
                    val accessToken = jwtTokenResponse?.accessToken ?: ""
                    val refreshToken = jwtTokenResponse?.refreshToken ?: ""
                    println( response.body())
                    //코루틴으로 비동기 처리하기
                    CoroutineScope(Dispatchers.Main).launch {
                        ApplicationClass.getInstance().getDataStore().saveTokens(accessToken, refreshToken)

                    }
                } else {
                    println("HTTP 오류: ${response.code()}")
                }
            }
            override fun onFailure(call: Call<JWTTokenResponse>, t: Throwable) {
                t.printStackTrace()
            }
        })

    }
}