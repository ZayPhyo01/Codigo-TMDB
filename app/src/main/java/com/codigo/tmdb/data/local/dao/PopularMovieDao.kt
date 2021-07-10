package com.codigo.tmdb.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codigo.tmdb.data.local.PopularMovieEntity
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import java.util.concurrent.Flow

@Dao
interface PopularMovieDao {

    @Query("select * from popular_movie_table  ")
    fun getAllPopularMovieList(): Observable<List<PopularMovieEntity>>

    @Query("select * from popular_movie_table")
    fun getAllPopularMovieNormalList(): List<PopularMovieEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveMovieList(list: List<PopularMovieEntity>)

    @Query("update popular_movie_table set isFavourite = :isFavourite where id =:id")
    fun updateFavouriteWithId(id: String, isFavourite: Boolean)

    @Query("select isFavourite from popular_movie_table where id =:id")
    fun getMovieDetailIsFavourite(id: String) : Boolean

    @Query("select * from popular_movie_table where id = :id")
    fun getPopularMovieDetailById(id : String) : Observable<PopularMovieEntity>




}