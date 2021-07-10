package com.codigo.tmdb.repositories.datastate

import com.codigo.tmdb.vos.MovieVO

sealed class UpComingMovieListState {
    data class Success(val list: List<MovieVO>) : UpComingMovieListState()
    data class Error(val throwable: Throwable) : UpComingMovieListState()
    object Loading : UpComingMovieListState()
}
