package com.app.tivi.repository.database

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.distinctUntilChanged
import com.app.tivi.repository.database.dao.FavouriteShowsDao
import com.app.tivi.repository.database.dao.GenreDao
import com.app.tivi.repository.database.entity.FavouriteShow
import com.app.tivi.repository.database.entity.Genre
import javax.inject.Inject
import javax.inject.Singleton


class DBHelper @Inject constructor(val favouriteShowsDao: FavouriteShowsDao){
    suspend fun updateFavourite(show: FavouriteShow) {
        if(show.isFavourite)
             favouriteShowsDao.addFavouriteShow(show)
        else
             favouriteShowsDao.deleteFavouriteShow(show);
    }

     suspend fun getFavShows() : List<FavouriteShow> {
         return favouriteShowsDao.getAllFavouriteShow();
    }

}