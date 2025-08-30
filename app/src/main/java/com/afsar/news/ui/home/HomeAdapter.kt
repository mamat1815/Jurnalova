package com.afsar.news.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

import com.afsar.news.data.Response.*
import com.afsar.news.databinding.ItemNewsBinding
import com.afsar.news.ui.detail.DetailActivity
import com.afsar.news.utils.DiffCallback
import com.bumptech.glide.Glide

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private val listNews = ArrayList<News>()

    fun setNews(news: List<News>) {
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
    ): HomeViewHolder {
        val view = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: HomeViewHolder,
        position: Int
    ) = holder.bind(listNews[position])

    override fun getItemCount(): Int = listNews.size

    inner class HomeViewHolder(private val binding: ItemNewsBinding):RecyclerView.ViewHolder(binding.root) {
        fun bind(data: News){
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