package com.example.praktikakotlin.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.praktikakotlin.dto.Post



class PostRepositoryInMemoryImpl: PostRepositpry {
    private var posts = listOf(
        Post(
        id = 1,
        author = "Бтпит. Техникум промышленных и информационных технологий",
        content = "«Борисоглебский техникум промышленных и информационных технологий» (ГБПОУ ВО «БТПИТ») — государственное бюджетное профессиональное образовательное учреждение Воронежской области.13 Некоторые специальности, которые предлагает техникум:44.02.01 — Дошкольное образование, квалификация — воспитатель детей дошкольного возраста; 344.02.05 — Коррекционная педагогика в начальном образовании, квалификация — учитель начальных классов; 338.02.01 — Экономика и бухгалтерский учёт (по отраслям), квалификации — бухгалтер, специалист по налогообложению, кассир, техник; 309.02.01 — Компьютерные системы и комплексы, квалификации — техник по компьютерным сетям и системам, системный администратор.",
        publish = "21 мая в 18:36",
        likeByMe = false,
        repost = 42432, likes = 324
    ),
        Post(
            id = 2,
            author = "Бтпит. Техникум промышленных и информационных технологий",
            content = " Адрес: ул. Третьяковская, д. 14, Борисоглебск. 35 Телефоны: 8 (47354) 6-05-73, 8 (47354) 6-06-98. E-mail: btpit@govvrn.ru.",
            publish = "23 мая в 12:16",
            likeByMe = false,
            repost = 424,
            likes = 4226
        ),
    )

    private  val  data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = data

    override fun likeById(id: Long) {
        posts = posts.map{
            if (it.id != id) it else it.copy(likeByMe =  !it.likeByMe,
                likes =  if (it.likeByMe) it.likes -1 else it.likes+1)
        }
        data.value = posts
    }

    override fun repostById(id: Long) {
        posts= posts.map {
            if(it.id != id) it else it.copy(
                repost = it.repost+1
            )
        }
        data.value = posts
    }

    override fun removeById(id: Long) {
        posts = posts.filter {it.id != id}
        data.value = posts
    }

    override fun save(post: Post) {
        if(post.id == 0L){
            posts= listOf(post.copy(
                id = (posts.size+1).toLong(),
                author = "Me",
                likeByMe = false,
                publish = "now",
                likes = 0,
                repost = 0
            )
        ) +posts
        data.value = posts
        return
        }
    posts = posts.map{
        if (it.id != post.id) it else it.copy(content =  post.content)
    }
        data.value = posts
    }

}


