package com.app.tivi.features.popular.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.tivi.databinding.PopularTvItemBinding
import com.app.tivi.features.uiModel.ShowListItem

class PopularTvAdapter(showClickListener: ShowClickListener) :
        ShowListAdapter(showClickListener){
     override fun updateFavourite(item: ShowListItem){
        val index = tvShows.indexOf(item);
        item.isFavourite = !item.isFavourite
        notifyItemChanged(index)
    }
}
