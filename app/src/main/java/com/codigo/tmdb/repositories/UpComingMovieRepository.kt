package com.codigo.tmdb.repositories

import com.codigo.tmdb.repositories.datastate.UpComingMovieListState
import com.codigo.tmdb.vos.MovieVO
import io.reactivex.Observable

interface UpComingMovieRepository {
    fun getUpComingMovieList () : Observable<UpComingMovieListState>
    fun updateFavouriteMovieWithId (id : String ,isFavourite : Boolean)
    fun getUpComingMovieDetailById (id : String) : Observable<MovieVO>
}