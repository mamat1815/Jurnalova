package com.afsar.news.data.retrofit

import com.afsar.news.data.Response
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {


    @GET("top-headlines?country=us")
    fun getNews(@Query("apiKey") apiKey: String): Call<Response.ListNews>

    @GET("everything?q=bitcoin")
    fun getPopularNews(@Query("apiKey") apiKey: String): Call<Response.ListNews>

    @GET("everything")
    fun searchNews(@Query("q") query: String, @Query("apiKey") apiKey: String): Call<Response.ListNews>
}