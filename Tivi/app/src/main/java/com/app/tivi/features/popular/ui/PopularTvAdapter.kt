package com.app.tivi.features.popular.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.tivi.databinding.PopularTvItemBinding
import com.app.tivi.repository.newtork.response.ShowIListItem

class PopularTvAdapter(val mShowClickListener: ShowClickListener) :
    RecyclerView.Adapter<PopularTvAdapter.ViewHolder>(),
    onItemsUpdated<ShowIListItem> {

    private var tvShows: List<ShowIListItem> = ArrayList()

    inner class ViewHolder(val binding: PopularTvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {


        fun bind(item: ShowIListItem) {
            binding.tvShow = item
            binding.showClickListner = mShowClickListener
        }


    }

    override fun getItemId(position: Int): Long {
        return getItem(position).id
    }

    private fun getItem(position: Int): ShowIListItem {
        return tvShows.get(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularTvAdapter.ViewHolder {
        val binding =
            PopularTvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: PopularTvAdapter.ViewHolder, position: Int) {
        val item = tvShows.get(position)

        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return tvShows.size
    }

    override fun updateItems(items: List<ShowIListItem>) {
        tvShows = items
        notifyDataSetChanged()
    }
}

class ShowClickListener(val clickAction: (id: Long) -> Unit) {
    fun onClick(show: ShowIListItem) {
        clickAction(show.id)
    }
}