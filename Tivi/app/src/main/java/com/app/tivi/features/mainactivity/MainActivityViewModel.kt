package com.app.tivi.features.mainactivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.tivi.utils.Event
import javax.inject.Inject


class MainActivityViewModel @Inject constructor(): ViewModel() {


    var mShowId = MutableLiveData<Event<Long>>();
    var mShowFavourites = MutableLiveData<Event<Boolean>>()
    var mFavItemsRemoved = MutableLiveData<ArrayList<Long>>();


    fun onShowClicked(showId: Long) {
        mShowId.value = Event(showId);

    }

    fun showFavourites() {
      mShowFavourites.value = Event(true);
    }
}