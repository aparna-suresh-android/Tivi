package com.app.tivi.features.favourites.di

import androidx.lifecycle.ViewModel
import com.app.tivi.di.ViewModelKey
import com.app.tivi.features.favourites.FavouriteShowsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface FavShowsViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(FavouriteShowsViewModel::class)
    fun bindPopularTvViewModel(viewModel: FavouriteShowsViewModel) : ViewModel;
}