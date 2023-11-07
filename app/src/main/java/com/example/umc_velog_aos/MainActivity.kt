package com.example.umc_velog_aos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.umc_velog_aos.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnMainLogin.setOnClickListener {

        }
    }
}