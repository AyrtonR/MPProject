package com.example.MPProject

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class NewsModel: ViewModel() {
    var news = mutableStateOf(listOf<News>())


    fun addNews(new: News){
        var newNews = news.value.toMutableList()
        newNews.add(new)
        news.value = newNews
    }

}