package com.afsar.news.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.afsar.news.data.Response.*
import com.afsar.news.data.source.DataSource
import com.afsar.news.data.source.RemoteDataSource

class Repository(private val remoteDataSource: RemoteDataSource): DataSource{
    override fun getNews(): LiveData<List<News>> {
        val listNews = MutableLiveData<List<News>>()
        remoteDataSource.getNewsList(object : RemoteDataSource.listNewsCallback{
            override fun onResponse(response: List<News>) {
                listNews.value = response
            }

        })
        return listNews
    }

    override fun getPopularNews(): LiveData<List<News>> {
        val listNews = MutableLiveData<List<News>>()
        remoteDataSource.getPopularNewsList(object : RemoteDataSource.listPopularNewsCallback{
            override fun onResponse(response: List<News>) {
                listNews.value = response
            }
            })
        return listNews
    }

    override fun searchNews(query: String): LiveData<List<News>> {
        val listNews = MutableLiveData<List<News>>()
        remoteDataSource.searchNewsList(query, object : RemoteDataSource.searchNewsCallback{
            override fun onResponse(response: List<News>) {
                listNews.value = response
            }
            })
        return listNews
    }
}
