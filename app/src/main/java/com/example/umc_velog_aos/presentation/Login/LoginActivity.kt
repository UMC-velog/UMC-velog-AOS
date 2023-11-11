package com.example.umc_velog_aos.presentation.Login

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
import com.example.umc_velog_aos.Util.hideKeyboard
import com.example.umc_velog_aos.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var textState = true

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        btnListener()
        emailWatcher(binding.etLogin)
    }

    private fun btnListener() {
        //로그인 버튼
        binding.btnLogin.setOnClickListener {
            //로그인 상태
            if (textState) {
            } else { //회원가입 상태
                binding.tvEmailSuccess.text = resources.getText(R.string.sign_up_link)
            }
            binding.linearEmailBlock.visibility = View.GONE
            binding.linearEmailBlockSuccess.visibility = View.VISIBLE
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
                binding.tvEmailSuccess.text = resources.getText(R.string.sign_up_link)
            } else {
                binding.tvLogin.text = resources.getText(R.string.sign_in)
                binding.tvEmailLogin.text = resources.getText(R.string.sign_in_email)
                binding.btnLogin.text = resources.getText(R.string.sign_in)
                binding.tvSocialLogin.text = resources.getText(R.string.sign_in_social)
                binding.tvSignupNotyet.text = resources.getText(R.string.sign_in_notyet)
                binding.tvSignup.text = resources.getText(R.string.sign_up_u)
                binding.tvEmailSuccess.text = resources.getText(R.string.sign_in_link)

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
}