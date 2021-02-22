package com.app.tivi.features.popular.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.tivi.databinding.PopularTvItemBinding
import com.app.tivi.features.uiModel.ShowListItem

abstract class ShowListAdapter(val mShowClickListener: ShowClickListener) :
    RecyclerView.Adapter<ShowListAdapter.ViewHolder>(),
    onItemsUpdated<ShowListItem> {


    protected var tvShows: ArrayList<ShowListItem> = ArrayList()

    inner class ViewHolder(val binding: PopularTvItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ShowListItem) {
            binding.tvShow = item
            binding.showClickListener = mShowClickListener
        }

    }

    override fun getItemId(position: Int): Long {
        return getItem(position).id
    }

    fun getItem(position: Int): ShowListItem {
        return tvShows.get(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        val binding =
            PopularTvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ShowListAdapter.ViewHolder, position: Int) {
        val item = tvShows.get(position)
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return tvShows.size
    }

    override fun updateItems(items: List<ShowListItem>) {
        tvShows = ArrayList(items)
        notifyDataSetChanged()
    }

    abstract fun updateFavourite(item: ShowListItem)

}

class ShowClickListener(
    val clickAction: (tvShow: ShowListItem, v: View) -> Unit
) {
    fun onClick(tvShow: ShowListItem, v: View) {
        clickAction(tvShow, v)
    }
}
