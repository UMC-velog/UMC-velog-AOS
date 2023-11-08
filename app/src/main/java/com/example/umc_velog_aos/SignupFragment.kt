package com.example.umc_velog_aos

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.umc_velog_aos.databinding.FragmentSignupBinding

class SignupFragment: Fragment() {
    private lateinit var binding: FragmentSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater, container, false)

        val textData: String = binding.tvSignupPolicy.text.toString()
        val builder = SpannableStringBuilder(textData)
        val colorSpan = ForegroundColorSpan(Color.parseColor("#12b886"))
        builder.setSpan(colorSpan, 0, 3, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        builder.setSpan(colorSpan, 6, 13, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)

        binding.tvSignupPolicy.text = builder


        return binding.root
    }
}