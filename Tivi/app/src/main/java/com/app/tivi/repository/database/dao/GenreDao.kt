package com.app.tivi.repository.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.app.tivi.repository.database.entity.Genre

@Dao
interface GenreDao {

    @Insert
    suspend fun addGenre(genre : Genre) : Long;

    @Insert
    suspend fun addGenres(genres : List<Genre>) : List<Long>

    @Query("SELECT * FROM genres")
    fun observeGenres() : LiveData<List<Genre>>;


    @Query("SELECT * FROM genres")
    suspend fun getAllGenres() : List<Genre>;

    @Query("SELECT * FROM genres where id = :id")
    suspend fun getGenre(id : Long) : Genre;

    @Query("SELECT * FROM genres where id in (:ids)")
    suspend fun getGenres(ids : List<Long>) : List<Genre>;
}