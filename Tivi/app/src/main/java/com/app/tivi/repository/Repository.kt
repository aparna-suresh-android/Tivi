package com.app.tivi.repository

import com.app.tivi.features.uiModel.ShowListItem
import com.app.tivi.repository.database.DBHelper
import com.app.tivi.repository.database.entity.FavouriteShow
import com.app.tivi.repository.newtork.Network
import com.app.tivi.repository.newtork.response.*
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val mNetwork: Network,
    private val mDBHelper: DBHelper
) {


    suspend fun getPopularTv(): List<ShowItemResponse>? = withContext(Dispatchers.IO) {
        val popularTvResponse = mNetwork.getPopular()
        return@withContext popularTvResponse?.results?.asList()
    }


    suspend fun getTvShowDetails(id: Long): ShowDetailsResponse? = withContext(Dispatchers.IO) {
        return@withContext mNetwork.getTvDetails(id)
    }

    suspend fun getSimilarShows(showId: Long): List<ShowItemResponse>? =
        withContext(Dispatchers.IO) {
            return@withContext mNetwork.getSimilarTv(showId)?.results?.asList()
        }

    suspend fun getShowCast(showId: Long): List<CastItemResponse>? = withContext(Dispatchers.IO) {
        return@withContext mNetwork.getCast(showId)?.cast?.asList()
    }

    suspend fun updateFavourite(show: ShowListItem) = withContext(Dispatchers.IO) {
        val favShow = FavouriteShow(
            show.id, show.name, show.posterPath,
            show.voteAverage, show.isFavourite
        )
        return@withContext mDBHelper.updateFavourite(favShow)
    }

    suspend fun getFavShows(): List<FavouriteShow> = withContext(Dispatchers.IO) {
        return@withContext mDBHelper.getFavShows()
    }
}
