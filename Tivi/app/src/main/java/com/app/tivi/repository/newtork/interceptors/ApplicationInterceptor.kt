package com.app.tivi.repository.newtork.interceptors

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject
import javax.inject.Named

class ApplicationInterceptor @Inject constructor(@Named("api_key") val mApiKey : String): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val oldReq = chain.request()
        val newUrl = oldReq.url.newBuilder()
            .addQueryParameter("api_key",mApiKey).build();
        val newReq = oldReq.newBuilder().url(newUrl).build();
        return chain.proceed(newReq);
    }
}