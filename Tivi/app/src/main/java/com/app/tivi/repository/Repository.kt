package com.app.tivi.repository

import com.app.tivi.repository.newtork.Network
import com.app.tivi.repository.newtork.response.*
import kotlinx.coroutines.*
import javax.inject.Inject


class Repository @Inject constructor(private val mNetwork: Network) {


    suspend fun getPopularTv(): List<ShowIListItem>? = withContext(Dispatchers.IO) {

        val popularTvResponse = mNetwork.getPopular()
        return@withContext popularTvResponse?.results?.asList()
    }



    suspend fun getTvShowDetails(id: Long): ShowDetails? = withContext(Dispatchers.IO) {

        return@withContext mNetwork.getTvDetails(id)
    }

    suspend fun getSimilarShows(showId: Long): List<ShowIListItem>? = withContext(Dispatchers.IO) {
        return@withContext mNetwork.getSimilarTv(showId)?.results?.asList()
    }

    suspend fun getShowCast(showId: Long): List<CastIListItem>? = withContext(Dispatchers.IO) {
        return@withContext mNetwork.getCast(showId)?.cast?.asList()
    }
}
