package com.codigo.tmdb.data.datasources

import com.codigo.tmdb.data.mappers.MovieListNetworkMapper
import com.codigo.tmdb.data.network.apis.MovieApi
import com.codigo.tmdb.vos.MovieVO
import io.reactivex.Observable
import javax.inject.Inject


class PopularMovieNetworkDataSourceImpl @Inject constructor(
    private val movieService: MovieApi,
    private val mapper: MovieListNetworkMapper
) :
    PopularMovieNetworkDataSource {

    override fun getPopularMovieList(page: Int): Observable<List<MovieVO>> {

        return movieService.getPopularMovies(page).map {
            mapper.map(it)
        }
    }

}