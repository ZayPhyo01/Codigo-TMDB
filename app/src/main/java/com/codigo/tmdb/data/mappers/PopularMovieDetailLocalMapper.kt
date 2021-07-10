package com.codigo.tmdb.data.mappers

import com.codigo.appbase.mappers.BidirectionalMap
import com.codigo.appbase.mappers.UnidirectionalMap
import com.codigo.tmdb.data.local.PopularMovieEntity
import com.codigo.tmdb.vos.MovieVO
import javax.inject.Inject

class PopularMovieDetailLocalMapper @Inject constructor(): UnidirectionalMap<PopularMovieEntity, MovieVO> {
    override fun map(data: PopularMovieEntity): MovieVO {
        return MovieVO(
            id = data.id,
            title = data.title,
            overview = data.overView,
            isFavourite = data.isFavourite,
            imageUrl = data.imageUrl
        )
    }


}
