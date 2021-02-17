package com.app.tivi.features.popular

import android.util.Log
import androidx.lifecycle.*

import com.app.tivi.repository.Repository
import com.app.tivi.repository.newtork.response.ShowIListItem
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularTvViewModel @Inject constructor(private val repository: Repository) : ViewModel() {


    val _tvShows = MutableLiveData<List<ShowIListItem>>()
    var isLoading = MutableLiveData<Boolean>(true)
    var isError = MutableLiveData<Boolean>(false)
    var noShows = MutableLiveData<Boolean>(false)
    var errorNoShowText = MutableLiveData<String>("")
    init {
        Log.i("aparna","init of vm");
        getPopularTvShows()
    }
    var tvShows : LiveData<List<ShowIListItem>> = _tvShows
    private fun getPopularTvShows() {
        isLoading.value = true
        noShows.value = false;
        isError.value = false;
        viewModelScope.launch {
            val result: List<ShowIListItem>? = repository.getPopularTv()
            Log.i("aparna","popular vm ${this@PopularTvViewModel}");
            if (result != null) {
                if (result.size > 0) {
                    _tvShows.value = result
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
    override fun onCleared() {
        super.onCleared()
        Log.i("aparna", "view model oncleated")

    }
}