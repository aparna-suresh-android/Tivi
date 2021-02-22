package com.app.tivi.features.tvDetails.di

import com.app.tivi.features.tvDetails.ui.ShowDetailsFragment
import dagger.Subcomponent

@Subcomponent(modules = [ShowDeatilsViewModelModule::class])
interface ShowDetailsComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create() : ShowDetailsComponent;
    }
    fun inject(fragment: ShowDetailsFragment)
}