package com.app.tivi.features.popular.ui

interface onItemsUpdated<in T> {
    fun  updateItems(t : List<T>);
}
