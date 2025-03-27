package com.example.praktikakotlin.dto

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.praktikakotlin.Repository.PostRepositoryFileImpl
import com.example.praktikakotlin.Repository.PostRepositpry
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
class PostViewModel(application: Application): AndroidViewModel(application){

        private val repositpry: PostRepositpry = PostRepositoryFileImpl(application)
        val data = repositpry.getAll()



        val edited = MutableLiveData(empty)

        fun save(){
                edited.value?.let {
                        repositpry.save(it)
                }
                edited.value = empty
        }
        fun
                edit(post: Post){
                edited.value = post
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