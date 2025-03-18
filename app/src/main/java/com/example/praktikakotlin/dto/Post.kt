package com.example.praktikakotlin.dto

data class Post (
    val  id: Long,
    val author: String,
    val content: String,
    val publish:String,
    val likeByMe:Boolean,
)
