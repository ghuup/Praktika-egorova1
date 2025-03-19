package com.example.praktikakotlin

import android.annotation.SuppressLint
import android.telephony.PhoneNumberUtils.formatNumber
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.praktikakotlin.ViewModel.PostViewModel
import com.example.praktikakotlin.databinding.ActivityPostCardBinding
import com.example.praktikakotlin.dto.Post

typealias  OnLikeListener = (post: Post) -> Unit
typealias  OnRepostListener = (post: Post) -> Unit

class PostsAdapter (
    private  val onLikeListener: OnLikeListener,
    private  val onRepostListener: OnRepostListener
) : ListAdapter<Post, PostViewHolder>(PostDiffCallback()){


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PostViewHolder {
        val binding = ActivityPostCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return  PostViewHolder(binding,onLikeListener,onRepostListener)
    }

    override fun onBindViewHolder(
        holder: PostViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }
}




