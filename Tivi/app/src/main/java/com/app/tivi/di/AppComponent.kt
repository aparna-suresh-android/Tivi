package com.app.tivi.di

import android.content.Context
import com.app.tivi.features.di.MainActivityComponent
import com.app.tivi.features.popular.di.PopularTvComponent
import com.app.tivi.features.tvDetails.di.ShowDetailsComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class,
                        AppSubComponents::class,
                        GenericViewModelFactoryModule::class])
interface AppComponent {



    @Component.Factory
    interface Factory{
        fun bindContext(@BindsInstance context : Context) : AppComponent;
    }

    fun getMainActivityComponent() : MainActivityComponent.Factory;
    fun getPopularTvComponent() : PopularTvComponent.Factory;
    fun getShowDetails() : ShowDetailsComponent.Factory;


}