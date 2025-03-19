package com.example.praktikakotlin.ViewModel

import androidx.lifecycle.ViewModel
import com.example.praktikakotlin.Repository.PostRepositoryInMemoryImpl
import com.example.praktikakotlin.Repository.PostRepositpry

class PostViewModel: ViewModel() {
   
        private val repositpry: PostRepositpry = PostRepositoryInMemoryImpl()
        val data = repositpry.getAll()
        fun likeById(id: Long) = repositpry.likeById(id)
        fun repostById(id: Long) = repositpry.repostById(id)
       
}