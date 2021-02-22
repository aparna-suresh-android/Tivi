package com.app.tivi.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.app.tivi.features.popular.ui.onItemsUpdated
import com.app.tivi.features.uiModel.IListItem
import com.squareup.picasso.Picasso


@BindingAdapter("items")
fun updateListItems(view: RecyclerView, tvShows: List<IListItem>?) {
    tvShows?.let {
        val adapter = view.adapter as onItemsUpdated<IListItem>
        adapter.updateItems(tvShows)
    }
}

    @BindingAdapter("imageUrl","error","placeholder")
    fun updateImageView(view : ImageView,url : String?,error : Drawable,placeholder: Drawable){
        url?.let {

            Picasso.get()
                    .load(url)
                    .error(error)
                    .placeholder(placeholder)
                    .fit()
                    .into(view);
        }

    }
