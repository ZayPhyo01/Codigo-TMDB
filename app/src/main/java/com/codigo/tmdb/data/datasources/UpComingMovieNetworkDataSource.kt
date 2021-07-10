package com.codigo.tmdb.data.datasources

import com.codigo.tmdb.vos.MovieVO
import io.reactivex.Observable

interface UpComingMovieNetworkDataSource {
    fun getUpComingMovieList (page : Int) : Observable<List<MovieVO>>
}