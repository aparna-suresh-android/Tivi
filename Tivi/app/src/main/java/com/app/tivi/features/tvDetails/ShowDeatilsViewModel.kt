package com.app.tivi.features.tvDetails

import android.os.Bundle
import androidx.lifecycle.*
import com.app.tivi.utils.IAssistedViewModelFactory
import com.app.tivi.repository.Repository
import com.app.tivi.repository.newtork.response.CastIListItem
import com.app.tivi.repository.newtork.response.ShowDetails
import com.app.tivi.repository.newtork.response.ShowIListItem
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ShowDeatilsViewModel @AssistedInject constructor(private val repo : Repository,
                                                        @Assisted private val args : Bundle?): ViewModel() {
    @AssistedFactory
    interface ShowDeatilsViewModelFactoryI : IAssistedViewModelFactory<ShowDeatilsViewModel> {
        override fun create(args : Bundle?): ShowDeatilsViewModel
    }
    private var _castList = MutableLiveData<List<CastIListItem>>();
    private var _similarShows = MutableLiveData<List<ShowIListItem>>();
     var showDetails = MutableLiveData<ShowDetails>();

    var showId : Long = -1L;
    var casts : LiveData<List<CastIListItem>> = _castList
    var similarShows : LiveData<List<ShowIListItem>> = _similarShows;

init {
    showId = args!!.getLong("showId");
    getShowDetails();
    getSimilarShows();
    getCast();

}


    private fun getShowDetails(){
        viewModelScope.launch{
            val details = async { repo.getTvShowDetails(showId) }
            showDetails.value = details.await();
        }
    }

    private fun getSimilarShows(){
        viewModelScope.launch {
            val similarShows = async { repo.getSimilarShows(showId) }
            _similarShows.value = similarShows.await();
        }
    }

    private fun getCast(){
        viewModelScope.launch {
            val showCast = async { repo.getShowCast(showId) }
            _castList.value = showCast.await();
        }
    }
}