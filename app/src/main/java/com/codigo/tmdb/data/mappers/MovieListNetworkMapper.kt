package com.codigo.tmdb.data.mappers

import com.codigo.appbase.mappers.UnidirectionalMap
import com.codigo.tmdb.constants.Constants
import com.codigo.tmdb.data.network.response.MovieResponse
import com.codigo.tmdb.vos.MovieVO
import javax.inject.Inject

class MovieListNetworkMapper @Inject constructor():
    UnidirectionalMap<MovieResponse, List<MovieVO>> {
    override fun map(data: MovieResponse): List<MovieVO> {
        return data.results.map {
            MovieVO(id = it.id , title = it.title, overview = it.overview , imageUrl = "${Constants.BASE_IMAGE_URL}${it.imageUrl}")
        }
    }
}