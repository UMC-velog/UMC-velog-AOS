package com.example.umc_velog_aos.presentation.signup

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.umc_velog_aos.data.dto.request.SignupRequest
import com.example.umc_velog_aos.databinding.FragmentSignupBinding
import com.example.umc_velog_aos.presentation.login.LoginActivity
import com.example.umc_velog_aos.service.ApiClient
import com.example.umc_velog_aos.service.SignupService
import com.google.gson.Gson
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        binding.tvSignupEmailHint.text = email
        setTextColor()
        btnListener()
        setupEditTextListeners()
        return binding.root
    }
    private fun btnListener() {
        val fragManager = parentFragmentManager.beginTransaction()
        //취소 버튼
        binding.btnSignupCancel.setOnClickListener {
            fragManager.remove(this).commitAllowingStateLoss()
        }
        //다음 버튼
        binding.btnSignupNext.setOnClickListener {
            postRegister()
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
    //텍스트 입력 시 버튼 활성화
    private fun setupEditTextListeners() {
        val editTexts = listOf(
            binding.etSignupName,
            binding.etSignupEmailId,
            binding.etSignupPassword,
            binding.etSignupRepassword
        )

        editTexts.forEach { editText ->
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    ableButton(editTexts)
                }
            })
        }
        binding.chkSignup.setOnCheckedChangeListener { _, _ -> ableButton(editTexts) }
    }
    private fun ableButton(editTexts: List<EditText>) {
        val allFilled = binding.chkSignup.isChecked && editTexts.all { it.text.isNotEmpty() }
        val passwordMatch = binding.etSignupPassword.text.toString() == binding.etSignupRepassword.text.toString()
        binding.btnSignupNext.isEnabled = allFilled && passwordMatch
    }
    private fun postRegister() {
        val client = ApiClient.getApiClient().create(SignupService::class.java)
        val signupRequest = SignupRequest(
            binding.etSignupName.text.toString(),
            binding.etSignupEmailId.text.toString(),
            binding.etSignupPassword.text.toString(),
            binding.tvSignupEmailHint.text.toString(),
            "USER"
        )
        val requestBody = Gson().toJson(signupRequest).toRequestBody("application/json".toMediaTypeOrNull())
        client.postRegister(requestBody).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    println("성공 ${response.code()}")
                } else {
                    println("HTTP 오류: ${response.code()}")
                }
            }
            override fun onFailure(call: Call<Void>, t: Throwable) {
                t.printStackTrace()
            }
        })
    }
}