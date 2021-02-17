package com.app.tivi.features.tvDetails.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.tivi.databinding.CastItemBinding
import com.app.tivi.features.popular.ui.onItemsUpdated
import com.app.tivi.repository.newtork.response.CastIListItem

class CastItemAdapter : RecyclerView.Adapter<CastItemAdapter.ViewHolder>(),
    onItemsUpdated<CastIListItem> {

    private var castItem: List<CastIListItem> = ArrayList()

    inner class ViewHolder(val binding: CastItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: CastIListItem) {
            binding.cast = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastItemAdapter.ViewHolder {
        val binding = CastItemBinding.inflate(LayoutInflater.from(parent.context),parent,false);
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CastItemAdapter.ViewHolder, position: Int) {
        val item = castItem.get(position)
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return castItem.size
    }

    override fun updateItems(items: List<CastIListItem>) {
        castItem = items
        notifyDataSetChanged()
    }
}