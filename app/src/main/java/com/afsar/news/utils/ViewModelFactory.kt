package com.afsar.news.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.afsar.news.data.Repository
import com.afsar.news.ui.home.HomeViewModel
import com.afsar.news.ui.search.SearchViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val dataRepository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(
                dataRepository
            ) as T

            modelClass.isAssignableFrom(SearchViewModel::class.java) -> SearchViewModel(
                dataRepository
            ) as T


//            modelClass.isAssignableFrom(AddPostViewModel::class.java) -> AddPostViewModel(
//                dataRepository
//            ) as T
//
//            modelClass.isAssignableFrom(HomeViewModel::class.java) -> HomeViewModel(
//                dataRepository
//            ) as T
//
//            modelClass.isAssignableFrom(DetailPostActivityViewModel::class.java) -> DetailPostActivityViewModel(
//                dataRepository
//            ) as T

            else

                -> throw IllegalArgumentException("Unknown ViewModel: " + modelClass.name)
        }

    }
}