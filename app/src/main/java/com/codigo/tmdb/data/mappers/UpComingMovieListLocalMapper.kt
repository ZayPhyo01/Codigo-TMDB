package com.codigo.tmdb.data.mappers

import com.codigo.appbase.mappers.BidirectionalMap
import com.codigo.tmdb.data.local.PopularMovieEntity
import com.codigo.tmdb.data.local.UpComingMovieEntity
import com.codigo.tmdb.vos.MovieVO
import javax.inject.Inject

class UpComingMovieListLocalMapper @Inject constructor() :
    BidirectionalMap<List<MovieVO>, List<UpComingMovieEntity>> {

    override fun map(data: List<MovieVO>): List<UpComingMovieEntity> {
        return data.map {
            UpComingMovieEntity(
                id = it.id,
                title = it.title,
                overView = it.overview,
                imageUrl = it.imageUrl
            )
        }
    }

    override fun reverseMap(data: List<UpComingMovieEntity>): List<MovieVO> {
        return data.map {
            MovieVO(
                id = it.id, title = it.title, overview = it.overView, isFavourite = it.isFavourite ,
                imageUrl =  it.imageUrl
            )
        }
    }
}