package com.codigo.tmdb.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "upcoming_movie_table")
data class UpComingMovieEntity(
    @ColumnInfo
    @PrimaryKey
    val id: String,
    @ColumnInfo
    val title: String,
    @ColumnInfo
    val overView: String,
    @ColumnInfo
    var isFavourite: Boolean = false,
    @ColumnInfo
    var imageUrl : String
)