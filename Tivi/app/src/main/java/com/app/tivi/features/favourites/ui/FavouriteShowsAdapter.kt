package com.app.tivi.features.favourites.ui

import com.app.tivi.features.popular.ui.ShowClickListener
import com.app.tivi.features.popular.ui.ShowListAdapter
import com.app.tivi.features.uiModel.ShowListItem

class FavouriteShowsAdapter(showClickListener: ShowClickListener)
    : ShowListAdapter(showClickListener) {
    override fun updateFavourite(item: ShowListItem){
        val index = tvShows.indexOf(item);
        tvShows.remove(item);
        notifyItemRemoved(index);
    }
}