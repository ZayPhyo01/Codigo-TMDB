package com.codigo.tmdb.data.datasources

import com.codigo.tmdb.vos.MovieVO
import io.reactivex.Maybe
import io.reactivex.Observable

interface UpComingLocalDataSource {
    fun getUpComingMovieList(): Observable<List<MovieVO>>
    fun getUpComingMovieDetailById(id : String) : Observable<MovieVO>
    fun saveUpComingMovieList (list : List<MovieVO>)
    fun updateFavouriteMovieWithId (id : String , isFavourite : Boolean)
}