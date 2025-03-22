package com.example.praktikakotlin

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethod
import android.view.inputmethod.InputMethodManager

object AndroidUtils {
    fun hideKeyboard(view: View){
        val imn = view.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imn.hideSoftInputFromWindow(view.windowToken,0)
    }

}