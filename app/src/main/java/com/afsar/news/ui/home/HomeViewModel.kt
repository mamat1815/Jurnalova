package com.afsar.news.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.afsar.news.data.Repository
import com.afsar.news.data.Response

class HomeViewModel(private val repository: Repository) : ViewModel() {

    fun getListNews(): LiveData<List<Response.News>> = repository.getNews()

    fun getListPopularNews(): LiveData<List<Response.News>> = repository.getPopularNews()
}