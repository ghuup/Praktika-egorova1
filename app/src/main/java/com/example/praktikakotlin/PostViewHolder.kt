package com.example.praktikakotlin

import androidx.recyclerview.widget.RecyclerView
import com.example.praktikakotlin.databinding.ActivityPostCardBinding
import com.example.praktikakotlin.dto.Post


class PostViewHolder(private val binding: ActivityPostCardBinding,
                     private val onLikelistener: OnLikeListener,
                     private  val onrepostlistener: OnRepostListener) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            publish.text = post.publish
            textView5.text = formatCount(post.repost)
            content.text = post.content
            likecol.text =  formatCount(post.likes)
            like.setImageResource(
                if (post.likeByMe) R.drawable.dislike else R.drawable.like
            )
            like.setOnClickListener {
                if (post.likeByMe) {
                    likecol.text = formatCount(post.likes)
                } else {
                    likecol.text = formatCount(post.likes)
                }
                onLikelistener(post)
            }
            shareButton.setOnClickListener {
                textView5.text = formatCount(post.repost)
                onrepostlistener(post)
            }
        }
    }
    fun formatCount(count: Int): String {
        return when {
            count >= 1_000_000 -> String.format("%.1fM", count / 1_000_000.0).replace(",","")
            count >= 1_000 -> String.format("%.1fK", count / 1_000.0)
            else -> count.toString()
        }
    }
}
