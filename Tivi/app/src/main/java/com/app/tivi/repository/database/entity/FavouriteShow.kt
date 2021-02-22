package com.app.tivi.repository.database.entity

import androidx.annotation.CheckResult
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "favouriteShows")
data class FavouriteShow @JvmOverloads constructor(
    @NotNull @PrimaryKey @ColumnInfo(name ="id") val id: Long,
    @NotNull @ColumnInfo(name ="vName") val name: String,
    @NotNull @ColumnInfo(name ="vPosterUrl") var posterUrl : String,
    @NotNull @ColumnInfo(name ="iVoteAverage") val voteAverage : Float,
    @Ignore val isFavourite : Boolean = true // Used to sync between uiModel and DbModel

)
