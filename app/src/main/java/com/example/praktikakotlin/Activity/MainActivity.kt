package com.example.praktikakotlin.Activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.lifecycle.ViewModel
import com.example.praktikakotlin.OnLikeListener
import com.example.praktikakotlin.PostsAdapter
import com.example.praktikakotlin.dto.Post
import com.example.praktikakotlin.R
import com.example.praktikakotlin.ViewModel.PostViewModel
import com.example.praktikakotlin.databinding.ActivityMainBinding
import com.example.praktikakotlin.databinding.ActivityPostCardBinding



class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var likesCount = 0
    private var sharesCount = 0
    private var prosmCount = 0
    private var isLiked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        val adapter = PostsAdapter (
            onLikeListener = {
            post -> viewModel.likeById(post.id)
        },
            onRepostListener = {
                    post -> viewModel.repostById(post.id)
            }
        )


        binding.container.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }
    }
}







