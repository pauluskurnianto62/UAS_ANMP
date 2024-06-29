package com.paulus.project_uas_anmp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.paulus.project_uas_anmp.databinding.NewsListItemBinding
import com.paulus.project_uas_anmp.model.News

class NewsListAdapter(val newsList:ArrayList<News>, val adapterOnClick : (News) -> Unit): RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>(), NewsDetailClick {
    class NewsViewHolder(var binding:NewsListItemBinding ): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.binding.news =newsList[position]
        holder.binding.readListener = this
        holder.binding.btnDelete.setOnClickListener {
                adapterOnClick(newsList[position])
        }
    }

    fun updateNewsList(newNewsList: List<News>) {
        newsList.clear()
        newsList.addAll(newNewsList)
        notifyDataSetChanged()
    }

    override fun onNewsDetailClick(v: View) {
        val action = NewsListFragmentDirections.actionNewsListNewsDetailFragment(v.tag.toString().toInt())
        Navigation.findNavController(v).navigate(action)
    }
}