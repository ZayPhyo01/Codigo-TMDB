package com.codigo.tmdb.data.mappers

import com.codigo.appbase.mappers.BidirectionalMap
import com.codigo.tmdb.data.local.PopularMovieEntity
import com.codigo.tmdb.vos.MovieVO
import javax.inject.Inject

class PopularMovieListLocalMapper @Inject constructor() :
    BidirectionalMap<List<MovieVO>, List<PopularMovieEntity>> {

    override fun map(data: List<MovieVO>): List<PopularMovieEntity> {
        return data.map {
            PopularMovieEntity(
                id = it.id,
                title = it.title,
                overView = it.overview,
                imageUrl = it.imageUrl
            )
        }
    }

    override fun reverseMap(data: List<PopularMovieEntity>): List<MovieVO> {
        return data.map {
            MovieVO(
                id = it.id, title = it.title, overview = it.overView, isFavourite = it.isFavourite,
                imageUrl = it.imageUrl
            )
        }
    }
}