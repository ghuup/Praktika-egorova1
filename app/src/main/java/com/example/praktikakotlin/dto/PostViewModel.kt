package com.example.praktikakotlin.dto

import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.praktikakotlin.Repository.PostRepositoryInMemoryImpl
import com.example.praktikakotlin.Repository.PostRepositpry

class PostViewModel: ViewModel() {

        private val repositpry: PostRepositpry = PostRepositoryInMemoryImpl()
        val data = repositpry.getAll()



        private  val  empty = Post(
                id = 0,
                content = "",
                author = "",
                likeByMe = false,
                publish = "",
                likes = 0,
                repost = 0,
                video = ""
        )

        val edited = MutableLiveData(empty)
        fun save(){
                edited.value?.let {
                        repositpry.save(it)
                }
                edited.value = empty
        }
        fun edit(post: Post){
                edited.value = post
        }

        fun changeContent(content: String){

                        val text = content.trim()
                        if (edited.value?.content == text){
                                return
                        }
                        edited.value = edited.value?.copy(content = text)

        }

        fun changeContentAndSave(content: String) {
                val text = content.trim()
                if (edited.value?.content == text) {
                        return
                }
                edited.value?.let {
                        repositpry.save(it.copy(content = text))
                }
                edited.value = empty
        }

        fun likeById(id: Long) = repositpry.likeById(id)
        fun repostById(id: Long) = repositpry.repostById(id)
        fun removeById(id: Long) = repositpry.removeById(id)
       
}