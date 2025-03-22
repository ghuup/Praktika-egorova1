package com.example.praktikakotlin.Activity

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.praktikakotlin.AndroidUtils
import com.example.praktikakotlin.OnInteractionListener
import com.example.praktikakotlin.R
import com.example.praktikakotlin.dto.PostsAdapter
import com.example.praktikakotlin.dto.PostViewModel
import com.example.praktikakotlin.databinding.ActivityMainBinding
import com.example.praktikakotlin.dto.Post

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        val viewModel: PostViewModel by viewModels()
        val adapter = PostsAdapter(
            onLikeListener = { post -> viewModel.likeById(post.id) },
            onRepostListener = { post -> viewModel.repostById(post.id) },
            object : OnInteractionListener {
                override fun onEdit(post: Post) {
                    binding.linearLayout.visibility = View.VISIBLE
                    viewModel.edit(post)
                }

                override fun onLike(post: Post) {
                    viewModel.likeById(post.id)
                }

                override fun onRemove(post: Post) {
                    viewModel.removeById(post.id)
                }
            }
        )

        binding.container.adapter = adapter
        viewModel.data.observe(this) { posts ->
            adapter.submitList(posts)
        }

        binding.save.setOnClickListener {
            with(binding.content) {
                if (text.isNullOrBlank()) {
                    Toast.makeText(
                        this@MainActivity,
                        "Content can not be empty",
                        Toast.LENGTH_SHORT
                    ).show()
                    return@setOnClickListener
                }
                viewModel.changeContent(text.toString())

                viewModel.save()


                binding.editText.text = text
                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
                binding.linearLayout.visibility = View.INVISIBLE

            }

        }

        binding.cancelButton.setOnClickListener {
            with(binding.content) {
                viewModel.changeContent(binding.editText.text.toString())
                viewModel.save()

                setText("")
                clearFocus()
                AndroidUtils.hideKeyboard(this)
                binding.linearLayout.visibility = View.INVISIBLE
            }
        }
        viewModel.edited.observe(this) { post ->
            if (post.id == 0L) {
                return@observe
            }
            with(binding.content) {
                requestFocus()
                setText(post.content)
            }
            with(binding.editText) {
                requestFocus()
                setText(post.content)
            }
        }
    }

}
