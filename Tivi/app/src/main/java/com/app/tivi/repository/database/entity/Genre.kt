package com.app.tivi.repository.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "genres")
data class Genre(
    @NotNull @PrimaryKey @ColumnInfo(name = "id") val id : Long,
    @NotNull @ColumnInfo(name = "vName") val name : String
    )