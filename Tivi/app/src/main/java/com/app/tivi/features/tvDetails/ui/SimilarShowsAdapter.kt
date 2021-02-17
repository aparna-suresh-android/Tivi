package com.app.tivi.features.tvDetails.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.tivi.databinding.SimilarTvShowItemBinding
import com.app.tivi.features.popular.ui.onItemsUpdated
import com.app.tivi.repository.newtork.response.ShowIListItem

class SimilarShowsAdapter : RecyclerView.Adapter<SimilarShowsAdapter.ViewHolder>(),
    onItemsUpdated<ShowIListItem> {

    private var similarShows: List<ShowIListItem> = ArrayList()

    inner class ViewHolder(val binding: SimilarTvShowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ShowIListItem) {
            binding.similarShow = item
        }


    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SimilarShowsAdapter.ViewHolder {
        val binding =
            SimilarTvShowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: SimilarShowsAdapter.ViewHolder, position: Int) {
        val item = similarShows.get(position)

        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return similarShows.size
    }

    override fun updateItems(items: List<ShowIListItem>) {
        similarShows = items
        notifyDataSetChanged()
    }


}