package com.app.tivi.features.popular

import androidx.lifecycle.*
import com.app.tivi.features.uiModel.ShowListItem
import com.app.tivi.repository.Repository
import com.app.tivi.repository.database.entity.FavouriteShow
import com.app.tivi.repository.newtork.response.ShowItemResponse
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularTvViewModel @Inject constructor(private val repository: Repository)
    : ViewModel() {


    var isLoading = MutableLiveData(true)
    var isError = MutableLiveData(false)
    var noShows = MutableLiveData(false)
    var errorNoShowText = MutableLiveData("")

    var tvShows = MediatorLiveData<List<ShowListItem>>()


    private var _favShowsFromDb = MutableLiveData<List<FavouriteShow>>();


    private var favShows: LiveData<List<ShowListItem>> = Transformations
        .map(_favShowsFromDb)
    { dbResult ->
        dbResult.map { favShow ->
            ShowListItem(favShow)
        }

    }

    private var _popularShows = MutableLiveData<List<ShowItemResponse>>()
    private var popularShows: LiveData<List<ShowListItem>> = Transformations
        .map(_popularShows)
    { networkResult ->
        networkResult.map { popularShow ->
            ShowListItem(popularShow)
        }

    }

    init {
        tvShows.addSource(favShows) {dbResult ->

                tvShows.value = combineResults()
        }
        tvShows.addSource(popularShows) {networkResult ->

            tvShows.value = combineResults()
        }
        getPopularTvShows()
    }


    private fun getPopularTvShows() {
        isLoading.value = true
        noShows.value = false
        isError.value = false
        viewModelScope.launch {
            val networkResult: List<ShowItemResponse>? = repository.getPopularTv()
            if (networkResult != null) {
                if (networkResult.isNotEmpty()) {
                    _popularShows.value = networkResult
                    _favShowsFromDb.value = repository.getFavShows()
                } else {
                    noShows.value = true
                    errorNoShowText.value = "No shows available"
                }
            } else {
                isError.value = true
                errorNoShowText.value = "Error in fetching tv shows"

            }
            isLoading.value = false
        }

    }


    private fun combineResults(): List<ShowListItem>? {
        if (favShows.value == null){
           return popularShows.value;
        }

        if(popularShows.value != null && popularShows.value!!.isNotEmpty()) {
            val list = ArrayList<ShowListItem>()

            favShows.value!!.forEach { item ->
                popularShows.value!!.find { item.id == it.id }?.isFavourite = true
            }

            list.addAll(popularShows.value!!)
            return list
        }else{
            return null;
        }
    }

    fun updateFavourite(show: ShowListItem) {
        viewModelScope.launch {
            repository.updateFavourite(show)
        }
    }


    fun updateShows(removedItemsKey: LongArray?) {
        removedItemsKey?.forEach { id ->
            popularShows.value!!.find { id == it.id }?.isFavourite = false
        }

    }

}