package com.app.tivi.features.favourites

import androidx.lifecycle.*
import com.app.tivi.features.uiModel.ShowListItem
import com.app.tivi.repository.Repository
import com.app.tivi.repository.database.entity.FavouriteShow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavouriteShowsViewModel @Inject
constructor(private val repository: Repository) : ViewModel() {

    var isLoading = MutableLiveData(true)
    var isError = MutableLiveData(false)
    var noShows = MutableLiveData(false)
    var errorNoShowText = MutableLiveData("")

    val _favShowsFromDb = MutableLiveData<List<FavouriteShow>>()

    val tvShows: LiveData<List<ShowListItem>> =
        Transformations.switchMap(_favShowsFromDb) { dbResult ->
            getShowListItems(dbResult)
        }
    var removedItemIds = MutableLiveData<ArrayList<Long>>()



    init {
        getFavouriteShows()
        removedItemIds.value = ArrayList()
    }

    private fun getFavouriteShows() {
        viewModelScope.launch {
            isLoading.value = true
            noShows.value = false
            isError.value = false
            val dbResult = repository.getFavShows()

            if (dbResult != null) {
                if (dbResult.isNotEmpty()) {
                    _favShowsFromDb.value = dbResult.toMutableList()
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

    private fun getShowListItems(result: List<FavouriteShow>):
            LiveData<List<ShowListItem>> {
        return MutableLiveData(result.map { item ->
            ShowListItem(item)
        })
    }

    fun updateFavourite(show: ShowListItem) {
        viewModelScope.launch {
            repository.updateFavourite(show)

            val favShow =
                _favShowsFromDb.value?.find { favShow -> favShow.id == show.id }

            val list = _favShowsFromDb.value?.toMutableList()
            list?.remove(favShow)
            _favShowsFromDb.value = list?.toList()


            _favShowsFromDb.value?.let {
                if (it.isEmpty()) {
                    noShows.value = true
                    errorNoShowText.value = "No shows available"
                }
            }

            val removedItemsList =  removedItemIds.value;
            removedItemsList?.add(show.id)
            removedItemsList?.let{
                removedItemIds.value = it;
            }
        }
    }

}