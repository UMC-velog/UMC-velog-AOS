package com.example.umc_velog_aos.presentation.Login

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.umc_velog_aos.R
import com.example.umc_velog_aos.databinding.ActivityLoginBinding

class LoginActivity: AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var state = true

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        btnListener()


        setContentView(binding.root)
    }
    private fun btnListener(){
        binding.btnLoginClose.setOnClickListener {
            finish()
        }
        //회원가입
        binding.tvSignup.setOnClickListener {
            if(state) {
                binding.tvLogin.text = resources.getText(R.string.sign_up)
                binding.tvEmailLogin.text = resources.getText(R.string.sign_up_email)
                binding.btnLogin.text = resources.getText(R.string.sign_up)
                binding.tvSocialLogin.text = resources.getText(R.string.sign_up_social)
                binding.tvSignupNotyet.text = resources.getText(R.string.sign_up_already)
                binding.tvSignup.text = resources.getText(R.string.sign_in_u)
            }else {
                binding.tvLogin.text = resources.getText(R.string.sign_in)
                binding.tvEmailLogin.text = resources.getText(R.string.sign_in_email)
                binding.btnLogin.text = resources.getText(R.string.sign_in)
                binding.tvSocialLogin.text = resources.getText(R.string.sign_in_social)
                binding.tvSignupNotyet.text = resources.getText(R.string.sign_in_notyet)
                binding.tvSignup.text = resources.getText(R.string.sign_up_u)
            }
            state = !state
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
    private fun textWatcher(){
        
    }
}