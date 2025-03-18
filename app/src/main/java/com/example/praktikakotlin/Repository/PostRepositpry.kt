package com.example.praktikakotlin.Repository

import androidx.lifecycle.LiveData
import com.example.praktikakotlin.dto.Post

interface PostRepositpry {
    fun get(): LiveData<Post>
    fun like()

}