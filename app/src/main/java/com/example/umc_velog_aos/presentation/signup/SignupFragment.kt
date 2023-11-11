package com.example.umc_velog_aos.presentation.signup

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.umc_velog_aos.databinding.FragmentSignupBinding
import com.example.umc_velog_aos.presentation.login.LoginActivity

class SignupFragment : Fragment() {
    private lateinit var binding: FragmentSignupBinding
    private var email: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        arguments?.let {
            email = it.getString("email")
        }
        val name = binding.etSignupName.text
        //val email = binding.tvSignupEmail.text
        val id = binding.etSignupEmailId.text
        val intro = binding.etSignupEmailInt.text
        binding.tvSignupEmail.text = email

        setTextColor()
        btnListener()
        return binding.root
    }

    private fun sendInfo(code: String) {

    }

    private fun btnListener() {
        val fragManager = parentFragmentManager.beginTransaction()
        //취소 버튼
        binding.btnSignupCancel.setOnClickListener {
            fragManager.remove(this).commitAllowingStateLoss()
        }
        //다음 버튼
        binding.btnSignupNext.setOnClickListener {
//            val client = Retrofit.Builder().baseUrl("http://umc.aolda.net")
//                .client(okHttpClient).
            //API 연결
            fragManager.remove(this).commitAllowingStateLoss()
            (activity as? LoginActivity)?.finish()
        }
    }
    //이용약관 개인정보취급방침 글자색 변경
    private fun setTextColor() {
        val textData: String = binding.tvSignupPolicy.text.toString()
        val spannable = SpannableString(textData)

        val startIndex1 = textData.indexOf("이용약관")
        val endIndex1 = startIndex1 + "이용약관".length
        spannable.setSpan(UnderlineSpan(), startIndex1, endIndex1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(
            ForegroundColorSpan(Color.parseColor("#12b886")),
            startIndex1,
            endIndex1,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        val startIndex2 = textData.indexOf("개인정보취급방침")
        val endIndex2 = startIndex2 + "개인정보취급방침".length
        spannable.setSpan(UnderlineSpan(), startIndex2, endIndex2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(
            ForegroundColorSpan(Color.parseColor("#12b886")),
            startIndex2,
            endIndex2,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        binding.tvSignupPolicy.text = spannable
    }

}