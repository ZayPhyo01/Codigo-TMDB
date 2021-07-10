package com.codigo.tmdb.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "popular_movie_table")
data class PopularMovieEntity(
    @ColumnInfo
    @PrimaryKey
    val id: String,
    @ColumnInfo
    val title: String,
    @ColumnInfo
    val overView: String,
    @ColumnInfo
    val imageUrl : String ,
    @ColumnInfo
    var isFavourite: Boolean = false
)