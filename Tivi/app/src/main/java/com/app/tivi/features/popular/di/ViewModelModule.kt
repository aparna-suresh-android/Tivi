package com.app.tivi.features.popular.di

import androidx.lifecycle.ViewModel
import com.app.tivi.di.ViewModelKey
import com.app.tivi.features.popular.PopularTvViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(PopularTvViewModel::class)
    fun bindPopularTvViewModel(viewModel: PopularTvViewModel) : ViewModel;
}