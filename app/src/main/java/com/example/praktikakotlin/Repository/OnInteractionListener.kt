package com.example.praktikakotlin.Repository

import com.example.praktikakotlin.dto.Post

interface OnInteractionListener {
    fun onEdit(post: Post)
    fun onLike(post: Post)
    fun onRemove(post: Post)
    fun onShare(post: Post)
}
