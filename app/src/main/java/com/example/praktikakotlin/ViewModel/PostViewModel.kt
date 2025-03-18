package com.example.praktikakotlin.ViewModel

import androidx.lifecycle.ViewModel
import com.example.praktikakotlin.Repository.PostRepositoryInMemoryImpl
import com.example.praktikakotlin.Repository.PostRepositpry

class PostViewModel: ViewModel() {
   
        private val repositpry: PostRepositpry = PostRepositoryInMemoryImpl()
        val data = repositpry.get()
        fun like() = repositpry.like()
       
}