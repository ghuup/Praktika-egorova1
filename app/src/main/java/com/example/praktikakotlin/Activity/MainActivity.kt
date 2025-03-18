package com.example.praktikakotlin.Activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.praktikakotlin.dto.Post
import com.example.praktikakotlin.R
import com.example.praktikakotlin.ViewModel.PostViewModel
import com.example.praktikakotlin.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var likesCount = 0
    private var sharesCount = 0
    private var prosmCount = 0
    private var isLiked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: PostViewModel by viewModels()
        viewModel.data.observe(this){post ->
            with(binding){
             author.text = post.author
                publish.text = post.publish
                content.text = post.content
                like.setImageResource(
                    if(post.likeByMe) R.drawable.dislike else R.drawable.like
                )
            }
        }



        binding.like.setOnClickListener {
            viewModel.like()
            toggleLike()
        }

        binding.shareButton.setOnClickListener {
            share()
        }



        binding.ProsmotrButton.setOnClickListener{
            prosm()
        }

        updateCounts()
    }

    private fun prosm() {
        prosmCount++
        updateCounts()
    }

    private fun toggleLike() {
        isLiked = !isLiked
        if (isLiked) {
            likesCount++
            binding.like.setImageResource(R.drawable.like)
        } else {
            likesCount--
            binding.like.setImageResource(R.drawable.dislike)
        }
        updateCounts()
    }

    private fun share() {
        sharesCount++
        updateCounts()
    }

    private fun updateCounts() {
        binding.likecol.text = formatCount(likesCount)
        binding.textView5.text = formatCount(sharesCount)
        binding.textView4.text = formatCount(prosmCount)
    }

    fun formatCount(count: Int): String {
        return when {
            count >= 1_000_000 -> String.format("%.1fM", count / 1_000_000.0).replace(",","")
            count >= 10_000 -> String.format("%.1fK", count / 10_000.0).replace(",","")
            count >= 1_000 -> String.format("%.1fK", count / 1_000.0).replace(",","")
            else -> count.toString()
        }
    }

}




