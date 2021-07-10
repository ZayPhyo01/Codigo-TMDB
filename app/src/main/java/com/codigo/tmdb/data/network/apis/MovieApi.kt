package com.codigo.tmdb.data.network.apis

import com.codigo.tmdb.data.network.response.MovieResponse
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("popular")
    fun getPopularMovies(

        @Query("page") page: Int
    ): Observable<MovieResponse>

    @GET ("upcoming")
    fun getUpComingMovies (@Query("page") page : Int) : Observable<MovieResponse>
}