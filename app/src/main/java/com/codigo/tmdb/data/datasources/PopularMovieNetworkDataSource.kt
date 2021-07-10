package com.codigo.tmdb.data.datasources

import com.codigo.tmdb.vos.MovieVO
import io.reactivex.Observable

interface PopularMovieNetworkDataSource {
    fun getPopularMovieList (page : Int) : Observable<List<MovieVO>>

}