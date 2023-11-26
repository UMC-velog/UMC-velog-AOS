package com.example.umc_velog_aos.presentation.post

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.umc_velog_aos.databinding.ActivityPostWriteBinding

class PostWriteActivity: AppCompatActivity() {
    private lateinit var binding: ActivityPostWriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityPostWriteBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        btnListener()
    }
    private fun btnListener() {
        binding.btnPostCancel.setOnClickListener {
            finish()
        }
        binding.btnPostSave.setOnClickListener {
            finish()
            //bundle 추가 후 profile 글보기
//            binding.etPostTitle
//            binding.etPostContent
        }

    }

}