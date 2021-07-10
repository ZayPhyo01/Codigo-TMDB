package com.codigo.tmdb.repositories.datastate

import com.codigo.tmdb.vos.MovieVO

sealed class PopularMovieListState {
    data class Success(val list: List<MovieVO>) : PopularMovieListState()
    data class Error(val throwable: Throwable) : PopularMovieListState()
    object Loading : PopularMovieListState()
}
