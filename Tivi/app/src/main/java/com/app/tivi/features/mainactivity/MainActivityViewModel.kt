package com.app.tivi.features.mainactivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import javax.inject.Inject


class MainActivityViewModel @Inject constructor(): ViewModel() {
    var mShowItemClicked = MutableLiveData<Boolean>();
    var mShowId : Long = -1L;


    fun onShowClicked(showId: Long) {
        mShowId = showId;
        mShowItemClicked.value = true
    }

    fun onShowClickDone() {
        mShowItemClicked.value = false;
    }
}