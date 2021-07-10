package com.codigo.tmdb.data.datasources

import com.codigo.tmdb.vos.MovieVO
import io.reactivex.Flowable
import io.reactivex.Observable

interface PopularMovieLocalDataSource {
    fun getPopularMovieList(): Observable<List<MovieVO>>
    fun getPopularMovieNormalList () : List<MovieVO>
    fun savePopularMovieList (list : List<MovieVO>)
    fun updateFavouriteMovieWithId (id : String , isFavourite : Boolean)
    fun getPopularMovieDetailById (id : String) : Observable<MovieVO>
}