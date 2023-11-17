package com.example.umc_velog_aos.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.umc_velog_aos.databinding.FragmentMainLatestBinding

class MainLatestFragment: Fragment() {
    private lateinit var binding: FragmentMainLatestBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainLatestBinding.inflate(inflater, container, false)


        return binding.root
    }
}