package com.app.tivi.features.tvDetails.di

import com.app.tivi.IAssistedViewModelFactory
import com.app.tivi.di.ViewModelKey
import com.app.tivi.features.tvDetails.ShowDeatilsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
interface ShowDeatilsViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(ShowDeatilsViewModel::class)
    fun bindShowDetailsViewModel(viewModel: ShowDeatilsViewModel.ShowDeatilsViewModelFactoryI) : IAssistedViewModelFactory<ShowDeatilsViewModel>
}

