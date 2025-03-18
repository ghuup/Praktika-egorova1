package com.example.praktikakotlin.Repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.praktikakotlin.dto.Post



class PostRepositoryInMemoryImpl: PostRepositpry {
    private var post = Post(
        id = 1,
        author = "Бтпит. Техникум промышленных и информационных технологий",
        content = "«Борисоглебский техникум промышленных и информационных технологий» (ГБПОУ ВО «БТПИТ») — государственное бюджетное профессиональное образовательное учреждение Воронежской области.13 Некоторые специальности, которые предлагает техникум:44.02.01 — Дошкольное образование, квалификация — воспитатель детей дошкольного возраста; 344.02.05 — Коррекционная педагогика в начальном образовании, квалификация — учитель начальных классов; 338.02.01 — Экономика и бухгалтерский учёт (по отраслям), квалификации — бухгалтер, специалист по налогообложению, кассир, техник; 309.02.01 — Компьютерные системы и комплексы, квалификации — техник по компьютерным сетям и системам, системный администратор. 3 Адрес: ул. Третьяковская, д. 14, Борисоглебск. 35 Телефоны: 8 (47354) 6-05-73, 8 (47354) 6-06-98. E-mail: btpit@govvrn.ru.",
        publish = "21 мая в 18:36",
        likeByMe = false
    )
    private  val  data = MutableLiveData(post)

    override fun get(): LiveData<Post> = data
    override fun like() {
        post = post.copy(likeByMe = !post.likeByMe)
        data.value = post
    }


}