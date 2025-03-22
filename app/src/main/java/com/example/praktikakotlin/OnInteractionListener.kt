package com.example.praktikakotlin

import com.example.praktikakotlin.dto.Post

interface OnInteractionListener {
    fun onLike(post: Post){}
    fun onEdit(post: Post){}
    fun onRemove(post: Post){}

}