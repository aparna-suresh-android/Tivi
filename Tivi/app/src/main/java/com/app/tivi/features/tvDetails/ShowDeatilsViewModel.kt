package com.app.tivi.features.tvDetails

import android.os.Bundle
import androidx.lifecycle.*
import com.app.tivi.features.uiModel.CastListItem
import com.app.tivi.features.uiModel.ShowDetails
import com.app.tivi.features.uiModel.ShowListItem
import com.app.tivi.repository.Repository
import com.app.tivi.repository.newtork.response.CastItemResponse
import com.app.tivi.repository.newtork.response.ShowDetailsResponse
import com.app.tivi.repository.newtork.response.ShowItemResponse
import com.app.tivi.utils.IAssistedViewModelFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ShowDeatilsViewModel @AssistedInject constructor(
    private val repo: Repository,
    @Assisted private val args: Bundle?
) : ViewModel() {
    @AssistedFactory
    interface ShowDeatilsViewModelFactoryI : IAssistedViewModelFactory<ShowDeatilsViewModel> {
        override fun create(args: Bundle?): ShowDeatilsViewModel
    }
    private var _castListResponse = MutableLiveData<List<CastItemResponse>>()
    private var _similarShowsResponse = MutableLiveData<List<ShowItemResponse>>()
    private var _showDetailsResponse = MutableLiveData<ShowDetailsResponse>()

    private var showId: Long = -1L

    var showDetails: LiveData<ShowDetails> = Transformations.switchMap(_showDetailsResponse)
    { networkResult -> getShowDetails(networkResult) }

    var casts: LiveData<List<CastListItem>> = Transformations.switchMap(_castListResponse)
    { networkResult ->
        getCastListItems(networkResult)

    }

    var similarShows: LiveData<List<ShowListItem>> =
        Transformations.switchMap(_similarShowsResponse)
        { networkResult ->
            getShowListItems(networkResult)
        }

    init {
        showId = args!!.getLong("showId")
        getShowDetailsFromRepo()
        getSimilarShowsFromRepo()
        getCastFromRespo()

    }

    private fun getShowDetails(networkResult: ShowDetailsResponse): LiveData<ShowDetails> {
        val uiItem = ShowDetails(networkResult)
        return MutableLiveData(uiItem)
    }

    private fun getShowListItems(networkResult: List<ShowItemResponse>): LiveData<List<ShowListItem>> {
        val list = networkResult.map { item ->
            ShowListItem(item)
        }
        return MutableLiveData(list)
    }

    private fun getCastListItems(networkResult: List<CastItemResponse>): LiveData<List<CastListItem>> {
        val list = networkResult.map { item ->
            CastListItem(item)
        }
        return MutableLiveData(list)
    }

    private fun getShowDetailsFromRepo() {
        viewModelScope.launch {
            val details = async { repo.getTvShowDetails(showId) }
            _showDetailsResponse.value = details.await()
        }
    }

    private fun getSimilarShowsFromRepo() {
        viewModelScope.launch {
            val similarShows = async { repo.getSimilarShows(showId) }
            _similarShowsResponse.value = similarShows.await()
        }
    }

    private fun getCastFromRespo() {
        viewModelScope.launch {
            val showCast = async { repo.getShowCast(showId) }
            _castListResponse.value = showCast.await()
        }
    }
}