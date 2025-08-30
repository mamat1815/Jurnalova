package com.afsar.news.data.source

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.afsar.news.BuildConfig
import com.afsar.news.data.Response
import com.afsar.news.data.retrofit.Api
import com.afsar.news.data.retrofit.Client
import retrofit2.Call
import retrofit2.Callback

class RemoteDataSource(private val retrofit: Api = Client.create()){
    companion object {
        private const val TIME: Long = 2000
    }
    private val apikey = BuildConfig.NEWS_APIKEY
    private val handler = Handler(Looper.getMainLooper())

    interface listNewsCallback {
        fun onResponse(response: List<Response.News>)
    }

    interface listPopularNewsCallback {
        fun onResponse(response: List<Response.News>)
    }

    interface searchNewsCallback {
        fun onResponse(response: List<Response.News>)
    }

    fun searchNewsList(query: String, callback: searchNewsCallback) {
        handler.postDelayed({
            retrofit.searchNews(query,apikey).enqueue(object : Callback<Response.ListNews> {
                override fun onResponse(
                    call: Call<Response.ListNews?>,
                    response: retrofit2.Response<Response.ListNews?>
                ) {
                    val final = response.body()?.articles
                    callback.onResponse(final!!)
                }

                override fun onFailure(
                    call: Call<Response.ListNews?>,
                    t: Throwable
                ) {
                    TODO("Not yet implemented")
                }

            })
        },TIME)

    }

    fun getPopularNewsList(callback: listPopularNewsCallback) {
        handler.postDelayed({
            retrofit.getPopularNews(apikey).enqueue(object : Callback<Response.ListNews> {
                override fun onResponse(
                    call: Call<Response.ListNews?>,
                    response: retrofit2.Response<Response.ListNews?>
                ) {
                    val final = response.body()
                    callback.onResponse(final!!.articles)
                    Log.d("TAG", "onResponse: $final")
                }

                override fun onFailure(
                    call: Call<Response.ListNews?>,
                    t: Throwable
                ) {
                    TODO("Not yet implemented")
                }
            })




        },TIME)
    }

    fun getNewsList(callback: listNewsCallback) {
        handler.postDelayed({
            retrofit.getNews(apikey).enqueue(object : Callback<Response.ListNews> {
                override fun onResponse(
                    call: Call<Response.ListNews?>,
                    response: retrofit2.Response<Response.ListNews?>
                ) {
                    val final = response.body()?.articles
                    callback.onResponse(final!!)
                }


                override fun onFailure(
                    call: Call<Response.ListNews?>,
                    t: Throwable
                ) {
                    TODO("Not yet implemented")
                }

            })
        },TIME)
    }
}