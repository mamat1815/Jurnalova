package com.afsar.news.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.afsar.news.data.Response
import com.afsar.news.databinding.ItemHeadlinesBinding
import com.afsar.news.ui.detail.DetailActivity
import com.afsar.news.utils.DiffCallback
import com.bumptech.glide.Glide

class HomePopularAdapter: RecyclerView.Adapter<HomePopularAdapter.HomePopularViewHolder>() {

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
    ): HomePopularAdapter.HomePopularViewHolder {
        val view = ItemHeadlinesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomePopularViewHolder(view)

    }

    override fun onBindViewHolder(
        holder: HomePopularAdapter.HomePopularViewHolder,
        position: Int
    ) = holder.bind(listNews[position])

    override fun getItemCount(): Int = listNews.size

    class HomePopularViewHolder(private val binding: ItemHeadlinesBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Response.News) {
            binding.apply {
                tvTitle.text = data.title
                tvNewsDate.text = data.publishedAt
                tvHeadlinesSource.text = data.source.name
                Glide.with(itemView.context)
                    .load(data.urlToImage)
                    .into(imgHeadlinesNews)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.NEWS_DATA, data.url)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

}