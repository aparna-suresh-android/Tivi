package com.app.tivi.features.di

import com.app.tivi.features.mainactivity.MainActivity
import dagger.Subcomponent


@Subcomponent(modules = [MainAcitivtyViewModelModule::class])
interface MainActivityComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create() : MainActivityComponent
    }

    fun inject(activity : MainActivity);
}