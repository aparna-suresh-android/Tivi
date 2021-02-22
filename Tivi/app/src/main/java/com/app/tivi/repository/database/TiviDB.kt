package com.app.tivi.repository.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.app.tivi.repository.database.dao.FavouriteShowsDao
import com.app.tivi.repository.database.entity.FavouriteShow
import javax.inject.Inject
import javax.inject.Singleton

@Database(entities = [FavouriteShow::class],version = 1)
abstract class TiviDB  : RoomDatabase(){


    abstract fun getFavouriteShowsDao() : FavouriteShowsDao;
}