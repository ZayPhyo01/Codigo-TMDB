package com.codigo.tmdb.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codigo.tmdb.data.local.dao.PopularMovieDao
import com.codigo.tmdb.data.local.dao.UpComingMovieDao

@Database(entities = arrayOf(PopularMovieEntity::class , UpComingMovieEntity::class), version = 10 , exportSchema = true)
abstract class MovieDataBase : RoomDatabase() {
    abstract fun getPopularMovieDao(): PopularMovieDao
    abstract fun getUpComingMovieDao () : UpComingMovieDao
}