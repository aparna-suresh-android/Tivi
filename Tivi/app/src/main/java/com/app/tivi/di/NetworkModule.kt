package com.app.tivi.di

import android.content.Context
import com.app.tivi.repository.newtork.Network
import com.app.tivi.repository.newtork.interceptors.ApplicationInterceptor
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull
import java.io.File
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun getOkHttp(cache: Cache,
                  applicationInterceptor: ApplicationInterceptor,
                ) : OkHttpClient {

        return OkHttpClient().newBuilder()
            .addInterceptor(applicationInterceptor)
            .cache(cache)
            .build()
    }


    @Provides
    fun getOkHttpCache(context : Context) : Cache{
        val directory = File(context.getCacheDir(), Network.CACHE_DIR_NAME);
        return Cache(directory = directory, Network.CACHE_SIZE)
    }

    @Provides
    fun getBaseHttpUrl(@Named("base_path") url : String) : HttpUrl{
        return url.toHttpUrlOrNull()!!;
    }

    @Singleton
    @Provides
    fun getGson(): Gson {
        return Gson().newBuilder()
            .setDateFormat("dd-MMM-yyyy")
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create()
    }

    @Provides
    @Named("base_path")
    fun getBasePath() : String{
        return Network.BASE_PATH;
    }

    @Provides
    @Named("api_key")
    fun getApiKey() : String{
        return Network.API_KEY;
    }


}