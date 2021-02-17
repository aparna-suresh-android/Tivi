package com.app.tivi.repository.newtork

import android.util.Log
import com.app.tivi.repository.newtork.response.*
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Network @Inject constructor(
    private val mOkHttpClient: OkHttpClient,
    private val mBaseUrl: HttpUrl,
    private val mGson: Gson
) {


    companion object {
        private const val PROTOCOL = "https"
        private const val BASE_URL = "api.themoviedb.org"
        private const val PATH = "3"
        const val BASE_PATH = "$PROTOCOL://$BASE_URL/$PATH"
        const val API_KEY = "8344fb5dae56784e4820fbdaeb24118d"

        const val CACHE_SIZE = 50L * 1024L * 1024L //50MB
        val CACHE_DIR_NAME = "http_cache"

        private const val IMAGE_BASE_URL = "image.tmdb.org"
        private const val IMAGE_POSTER_PATH = "t/p/w154"

        const val IMAGE_POSTER_URL = "$PROTOCOL://$IMAGE_BASE_URL/$IMAGE_POSTER_PATH"
    }


    fun getPopular(): ShowListResponse? {
        val httpUrl = mBaseUrl.newBuilder().addPathSegments(Apis.POPULAR).build()
        var req = Request.Builder().url(httpUrl).build()

        try {
            val response: Response = mOkHttpClient.newCall(req).execute()
            response.body?.let { responseBody ->
                val popularTvs = mGson
                    .fromJson(responseBody.string(), ShowListResponse::class.java)

                responseBody.close()
                return popularTvs
            }
        } catch (e: IOException) {
            return null
        }
        return null
    }


    fun getGenres(): GenreResponse? {
        Log.i("aparna", "getGenres")
        val httpUrl = mBaseUrl.newBuilder().addPathSegments(Apis.GENRE).build()

        var req = Request.Builder().url(httpUrl).build()

        try {
            val response: Response = mOkHttpClient.newCall(req).execute()
            response.body?.let { responseBody ->
                val genreResponse = mGson
                    .fromJson(responseBody.string(), GenreResponse::class.java)
//                Log.i("aparna","${genreResponse.genres}")
                responseBody.close()
                return genreResponse
            }
        } catch (e: IOException) {
            return null
        }
        return null
    }


    fun getCast(id: Long): CastResponse? {
        val url = String.format(Apis.AGGREGATE_CREDITS, id)
        val httpUrl = mBaseUrl.newBuilder()
            .addPathSegments(url)
            .build()

        var req = Request.Builder().url(httpUrl).build()

        try {
            val response: Response = mOkHttpClient.newCall(req).execute()
            response.body?.let { responseBody ->
                val castResponse = mGson
                    .fromJson(responseBody.string(), CastResponse::class.java)

                responseBody.close()
                return castResponse
            }
        } catch (e: IOException) {
            return null
        }
        return null
    }

    fun getSimilarTv(id: Long): ShowListResponse? {

        val url = String.format(Apis.SIMILAR_SHOWS, id)
        val httpUrl = mBaseUrl.newBuilder()
            .addPathSegments(url)
            .build()

        var req = Request.Builder().url(httpUrl).build()

        try {
            val response: Response = mOkHttpClient.newCall(req).execute()
            response.body?.let { responseBody ->
                val showListResponse = mGson
                    .fromJson(responseBody.string(), ShowListResponse::class.java)

                responseBody.close()
                return showListResponse
            }
        } catch (e: IOException) {
            return null
        }
        return null
    }


    fun getTvDetails(id: Long): ShowDetails? {
        Log.i("aparna", "n/w getTvDetails ${this@Network}")
        val httpUrl = mBaseUrl.newBuilder()
            .addPathSegments(Apis.SHOW_DETAILS + "/${id}")
            .build()

        var req = Request.Builder().url(httpUrl).build()

        try {
            val response: Response = mOkHttpClient.newCall(req).execute()
            response.body?.let { responseBody ->
                val showDetails = mGson
                    .fromJson(responseBody.string(), ShowDetails::class.java)

                responseBody.close()
                return showDetails
            }
        } catch (e: IOException) {
            return null
        }
        return null
    }
}