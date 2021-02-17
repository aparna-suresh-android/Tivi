package com.app.tivi.features.mainactivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.tivi.utils.Event
import javax.inject.Inject


class MainActivityViewModel @Inject constructor(): ViewModel() {
    var mShowItemClicked = MutableLiveData<Event<Boolean>>();
    var mShowId = MutableLiveData<Event<Long>>();


    fun onShowClicked(showId: Long) {
        mShowId.value = Event(showId);
//        mShowItemClicked.value = Event(true)
    }
}