package com.codigo.tmdb.data.mappers

import com.codigo.appbase.mappers.UnidirectionalMap
import com.codigo.tmdb.data.local.PopularMovieEntity
import com.codigo.tmdb.data.local.UpComingMovieEntity
import com.codigo.tmdb.vos.MovieVO
import javax.inject.Inject

class UpComingMovieDetailLocalMapper @Inject constructor() :
    UnidirectionalMap<UpComingMovieEntity, MovieVO> {
    override fun map(data: UpComingMovieEntity): MovieVO {
        return MovieVO(
            id = data.id,
            title = data.title,
            overview = data.overView,
            isFavourite = data.isFavourite,
            imageUrl = data.imageUrl
        )
    }


}