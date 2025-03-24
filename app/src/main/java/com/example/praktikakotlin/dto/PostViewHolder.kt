package com.example.praktikakotlin.dto

import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.praktikakotlin.OnInteractionListener
import com.example.praktikakotlin.R
import com.example.praktikakotlin.databinding.ActivityPostCardBinding


class PostViewHolder(
    private val binding: ActivityPostCardBinding,
    private val onLikelistener: OnLikeListener,
    private val onrepostlistener: OnRepostListener,
    private val  onInteractionListener: OnInteractionListener,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(post: Post) {
        binding.apply {
            author.text = post.author
            publish.text = post.publish
            textView5.text = formatCount(post.repost)
            content.text = post.content
            likecol.text =  formatCount(post.likes)
            like.isChecked = post.likeByMe
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



            menu.setOnClickListener {
                PopupMenu(it.context, it).apply {
                    inflate(R.menu.menu_option)
                    setOnMenuItemClickListener { item ->
                        when (item.itemId){
                            R.id.remove -> {
                                onInteractionListener.onRemove(post)
                                true
                            }
                            R.id.edit -> {
                                onInteractionListener.onEdit(post)
                                true
                            }
                            else -> false
                        }
                    }
                }.show()
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
