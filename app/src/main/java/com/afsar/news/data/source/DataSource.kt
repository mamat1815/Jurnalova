package com.afsar.news.data.source

import androidx.lifecycle.LiveData
import com.afsar.news.data.Response

interface DataSource {

    fun getNews(): LiveData<List<Response.News>>
    fun getPopularNews(): LiveData<List<Response.News>>
    fun searchNews(query: String): LiveData<List<Response.News>>
}