package com.example.umc_velog_aos.util

import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

fun EditText.validationWatcher(validationFunction: (String) -> Boolean) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            val text = this@validationWatcher.text.toString()
            val isValid = validationFunction(text)
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
    })
}