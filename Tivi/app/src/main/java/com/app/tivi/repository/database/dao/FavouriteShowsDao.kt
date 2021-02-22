package com.app.tivi.repository.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.app.tivi.repository.database.entity.FavouriteShow

@Dao
interface FavouriteShowsDao {
    @Insert
    suspend fun addFavouriteShow(show: FavouriteShow): Long

    @Delete
    suspend fun deleteFavouriteShow(show: FavouriteShow): Int

    @Query("SELECT * FROM favouriteShows")
     suspend fun getAllFavouriteShow(): List<FavouriteShow>


}