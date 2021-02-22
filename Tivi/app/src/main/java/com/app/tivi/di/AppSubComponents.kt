package com.app.tivi.di

import com.app.tivi.features.di.MainActivityComponent
import com.app.tivi.features.favourites.di.FavouriteShowsComponent
import com.app.tivi.features.popular.di.PopularTvComponent
import com.app.tivi.features.tvDetails.di.ShowDetailsComponent
import dagger.Module

@Module(subcomponents = [PopularTvComponent::class,
    ShowDetailsComponent::class,
    MainActivityComponent::class,
    FavouriteShowsComponent::class])
class AppSubComponents