package com.app.tivi.di

import android.content.Context
import androidx.room.Room
import com.app.tivi.repository.database.DBHelper
import com.app.tivi.repository.database.TiviDB
import com.app.tivi.repository.database.dao.FavouriteShowsDao
import com.app.tivi.repository.database.dao.GenreDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun getDatabase(context: Context): TiviDB {
        return Room.databaseBuilder(context, TiviDB::class.java, "tividb").build()
    }


    @Singleton
    @Provides
    fun getGenreDao(db : TiviDB): FavouriteShowsDao {
        return db.getFavouriteShowsDao();
    }


}