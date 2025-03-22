package com.example.praktikakotlin.Repository

import androidx.lifecycle.LiveData
import com.example.praktikakotlin.dto.Post

interface PostRepositpry {

    fun getAll(): LiveData<List<Post>>
    fun likeById(id: Long)
    fun repostById(id: Long)
    fun removeById(id: Long)
    fun save(post: Post)
}