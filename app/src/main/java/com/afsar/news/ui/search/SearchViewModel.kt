package com.afsar.news.ui.search

import androidx.lifecycle.ViewModel
import com.afsar.news.data.Repository

class SearchViewModel(private val repository: Repository) : ViewModel() {
    fun searchNews(query: String) = repository.searchNews(query)

}