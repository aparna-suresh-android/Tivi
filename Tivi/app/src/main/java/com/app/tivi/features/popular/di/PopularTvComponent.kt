package com.app.tivi.features.popular.di

import com.app.tivi.features.popular.ui.PopularFragment
import dagger.Subcomponent


@Subcomponent(modules = [ViewModelModule::class])
interface PopularTvComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create() : PopularTvComponent;
    }

    fun injectPopularTvFragment(frag : PopularFragment);
}