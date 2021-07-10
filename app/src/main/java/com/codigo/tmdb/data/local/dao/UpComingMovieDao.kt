package com.codigo.tmdb.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.codigo.tmdb.data.local.PopularMovieEntity
import com.codigo.tmdb.data.local.UpComingMovieEntity
import io.reactivex.Maybe
import io.reactivex.Observable

@Dao
interface UpComingMovieDao {

    @Query("select * from upcoming_movie_table  ")
    fun getAllUpComingMovieList(): Observable<List<UpComingMovieEntity>>


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun saveMovieList(list: List<UpComingMovieEntity>)

    @Query("update upcoming_movie_table set isFavourite = :isFavourite where id =:id")
    fun updateFavouriteWithId(id: String, isFavourite: Boolean)

    @Query("select isFavourite from upcoming_movie_table where id =:id")
    fun getMovieDetailIsFavourite(id: String) : Boolean

    @Query("select * from upcoming_movie_table where id =:id")
    fun getUpComingMovieDetailById (id : String) : Observable<UpComingMovieEntity>



}