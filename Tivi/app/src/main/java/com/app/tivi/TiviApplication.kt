package com.app.tivi

import android.app.Application
import com.app.tivi.di.AppComponent
import com.app.tivi.di.DaggerAppComponent

class TiviApplication : Application() {
    lateinit var mAppComponent : AppComponent;
    override fun onCreate() {
        super.onCreate()
        mAppComponent = DaggerAppComponent.factory().bindContext(this);
    }

}