package com.app.tivi.features.favourites.di

import com.app.tivi.features.favourites.ui.FavouriteShowsFragment
import dagger.Subcomponent

@Subcomponent(modules = [FavShowsViewModelModule::class])
interface FavouriteShowsComponent {

    @Subcomponent.Factory
    interface  Factory{
        fun create() : FavouriteShowsComponent
    }

    fun inject(fragment: FavouriteShowsFragment);
}