package com.example.Praktikakotlin.Activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.praktikakotlin.R
import com.example.praktikakotlin.Repository.NewPostResultContract
import com.example.praktikakotlin.Repository.OnInteractionListener
import com.example.praktikakotlin.databinding.FragmentFeedBinding
import com.example.praktikakotlin.dto.PostsAdapter
import com.example.praktikakotlin.dto.PostViewModel

import com.example.praktikakotlin.dto.Post

class FeedFragment : Fragment() {
    private val viewModel: PostViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFeedBinding.inflate(inflater, container, false)

        val adapter = PostsAdapter(
            onLikeListener = { post -> viewModel.likeById(post.id) },
            onRepostListener = { post ->
                val intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, post.content)
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(
                    intent,
                    getString(R.string.chooser_share_post)
                )
                startActivity(shareIntent)
                viewModel.repostById(post.id)
            },

            object : OnInteractionListener {
                override fun onEdit(post: Post) {
                    viewModel.edit(post)
                }

                override fun onLike(post: Post) {
                    viewModel.likeById(post.id)
                }

                override fun onRemove(post: Post) {
                    viewModel.removeById(post.id)
                }


                //override fun onShare(post: Post) {
//
                //    val intent = Intent().apply {
                //        action = Intent.ACTION_SEND
                //        putExtra(Intent.EXTRA_TEXT, post.content)
                //        type = "text/plain"
                //    }
                //    val shareIntent = Intent.createChooser(
                //        intent,
                //        getString(R.string.chooser_share_post)
                //    )
                //    startActivity(shareIntent)
                //}
            }
        )

        binding.container.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner) { posts ->
            adapter.submitList(posts)
        }

        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_feedFragment_to_newPostFragment)
        }

        return binding.root
    }
}
