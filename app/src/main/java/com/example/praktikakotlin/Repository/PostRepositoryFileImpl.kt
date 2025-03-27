package com.example.praktikakotlin.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.praktikakotlin.dto.Post
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken



class PostRepositoryFileImpl(
    private  val context: Context,
    ) : PostRepositpry {

    private val gson = Gson()
    private val type = TypeToken.getParameterized(List::class.java, Post::class.java).type
    private  val filename = "posts.json"
    private var nextId = 3L
    private var posts = emptyList<Post>()
    private val data = MutableLiveData(posts)
    
    init {
       val file = context.filesDir.resolve(filename)
        if(file.exists()){
            context.openFileInput(filename).bufferedReader().use {
                posts = gson.fromJson(it,type)
                data.value = posts
            }
        }else{
            sync()
        }
    }
    

    
    override fun getAll(): LiveData<List<Post>> = data
    
    

    override fun likeById(id: Long) {
        posts = posts.map{
            if (it.id != id) it else it.copy(likeByMe =  !it.likeByMe,
                likes =  if (it.likeByMe) it.likes -1 else it.likes+1)
        }
        data.value = posts
        sync()
    }

    override fun repostById(id: Long) {
        posts= posts.map {
            if(it.id != id) it else it.copy(
                repost = it.repost+1
            )
        }
        data.value = posts
        sync()
    }

    override fun removeById(id: Long) {
        posts = posts.filter {it.id != id}
        data.value = posts
        sync()
    }

    override fun save(post: Post) {
        if(post.id == 0L){
            posts= listOf(post.copy(
                id = nextId++,
                author = "Me",
                likeByMe = false,
                publish = "now",
                likes = 0,
                repost = 0
            )
        ) +posts
        data.value = posts
            sync()
        return
        }
    posts = posts.map{
        if (it.id != post.id) it else it.copy(content =  post.content)
    }
        data.value = posts
        sync()
    }
    private fun sync(){
        context.openFileOutput(filename, Context.MODE_PRIVATE).bufferedWriter().use {
            it.write(gson.toJson(posts))
        }
    }



}


