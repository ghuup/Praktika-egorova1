package com.example.praktikakotlin.dto

import android.view.View
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.praktikakotlin.R
import com.example.praktikakotlin.Repository.OnInteractionListener
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


            if (post.video == null){
                videoView.visibility = View.GONE
                playBtn.visibility = View.GONE
            }
            playBtn.setOnClickListener{
                post.video?.let { it1 -> videoView.loadUrl(it1) }
            }

            like.setOnClickListener {
                if (post.likeByMe) {
                    likecol.text = formatCount(post.likes)
                } else {
                    likecol.text = formatCount(post.likes)
                }
                onLikelistener(post)
            }

            share.setOnClickListener {
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
            like.setOnClickListener {
                onInteractionListener.onLike(post)
            }

            //share.setOnClickListener {
            //    onInteractionListener.onShare(post)
            //}
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
