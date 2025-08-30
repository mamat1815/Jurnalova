package com.afsar.news.utils

import androidx.recyclerview.widget.DiffUtil
import com.afsar.news.data.Response

class DiffCallback(private val mOldDataList: List<Response.News>, private val mNewDataList: List<Response.News>) : DiffUtil.Callback()  {


    override fun getOldListSize(): Int = mOldDataList.size

    override fun getNewListSize(): Int = mNewDataList.size

    override fun areItemsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        return mOldDataList[oldItemPosition].url == mNewDataList[newItemPosition].url
    }

    override fun areContentsTheSame(
        oldItemPosition: Int,
        newItemPosition: Int
    ): Boolean {
        val oldList = mOldDataList[oldItemPosition]
        val newList = mNewDataList[newItemPosition]
        return oldList.url == newList.url
    }
}
