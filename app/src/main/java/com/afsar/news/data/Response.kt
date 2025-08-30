package com.afsar.news.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

class Response {

    @Parcelize
    data class News(
        @SerializedName("source")
        val source: Source,
        @SerializedName("author")
        val author: String,
        @SerializedName("title")
        val title: String,
        @SerializedName("description")
        val description: String,
        @SerializedName("url")
        val url: String,
        @SerializedName("urlToImage")
        val urlToImage: String,
        @SerializedName("publishedAt")
        val publishedAt: String,
        @SerializedName("content")
        val content: String,

    ) : Parcelable

    @Parcelize
    data class Source(
        val id: String?,
        val name: String
    ) : Parcelable

    @Parcelize
    data class ListNews(
        @SerializedName("status")
        val status: String,
        @SerializedName("totalResults")
        val totalResults: Int,
        @SerializedName("articles")
        val articles: List<News>
    ) : Parcelable


}