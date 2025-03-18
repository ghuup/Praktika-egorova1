package com.example.praktikakotlin

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.praktikakotlin.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var likesCount = 0
    private var sharesCount = 0
    private var prosmCount = 9
    private var isLiked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.LikeButton.setOnClickListener {
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
            binding.LikeButton.setImageResource(R.drawable.like)
        } else {
            likesCount--
            binding.LikeButton.setImageResource(R.drawable.dislike)
        }
        updateCounts()
    }

    private fun share() {
        sharesCount++
        updateCounts()
    }

    private fun updateCounts() {
        binding.textView6.text = formatCount(likesCount)
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