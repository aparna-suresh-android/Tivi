package com.app.tivi.features.di

import androidx.lifecycle.ViewModel
import com.app.tivi.di.ViewModelKey
import com.app.tivi.features.mainactivity.MainActivityViewModel
import com.app.tivi.features.popular.PopularTvViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface MainAcitivtyViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MainActivityViewModel::class)
    fun bindPopularTvViewModel(viewModel: MainActivityViewModel) : ViewModel;
}