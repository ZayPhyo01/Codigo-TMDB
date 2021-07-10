package com.codigo.tmdb.repositories

import com.codigo.tmdb.repositories.datastate.PopularMovieListState
import com.codigo.tmdb.vos.MovieVO
import io.reactivex.Flowable
import io.reactivex.Observable

interface PopularMovieRepository {
    fun getPopularList () : Observable<PopularMovieListState>
    fun getPopularMovieDetailById (id : String) : Observable<MovieVO>
    fun updateFavouriteMovieWithId (id : String ,isFavourite : Boolean)
}