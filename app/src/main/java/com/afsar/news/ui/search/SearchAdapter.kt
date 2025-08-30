package com.afsar.news.ui.search

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.afsar.news.data.Response
import com.afsar.news.databinding.ItemNewsBinding
import com.afsar.news.ui.detail.DetailActivity
import com.afsar.news.utils.DiffCallback
import com.bumptech.glide.Glide

class SearchAdapter : RecyclerView.Adapter<SearchAdapter.SearchViewHolder>()  {

    private val listNews = ArrayList<Response.News>()

    fun setNews(news: List<Response.News>) {
        val diffCallback = DiffCallback(this.listNews, news)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listNews.apply {
            clear()
            addAll(news)
        }
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SearchAdapter.SearchViewHolder {
        val view = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SearchViewHolder(view)
    }

    override fun onBindViewHolder(holder: SearchAdapter.SearchViewHolder, position: Int) = holder.bind(listNews[position])

    override fun getItemCount(): Int = listNews.size

    class SearchViewHolder(private val binding: ItemNewsBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Response.News){
            binding.apply {
                tvNewsTitle.text = data.title
                tvNewsDate.text = data.publishedAt
                tvNewsSource.text = data.source.name

                Glide.with(itemView.context)
                    .load(data.urlToImage)
                    .into(imgNewsNews)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.NEWS_DATA, data.url)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}