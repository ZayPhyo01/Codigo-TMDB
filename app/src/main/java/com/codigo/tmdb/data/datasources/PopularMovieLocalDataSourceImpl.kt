package com.codigo.tmdb.data.datasources

import com.codigo.tmdb.data.local.dao.PopularMovieDao
import com.codigo.tmdb.data.mappers.PopularMovieDetailLocalMapper
import com.codigo.tmdb.data.mappers.PopularMovieListLocalMapper
import com.codigo.tmdb.vos.MovieVO
import io.reactivex.Flowable
import io.reactivex.Observable
import javax.inject.Inject

class PopularMovieLocalDataSourceImpl @Inject constructor(
    private val daoPopular: PopularMovieDao,
    private val mapper: PopularMovieListLocalMapper,
    private val detailMapper: PopularMovieDetailLocalMapper
) : PopularMovieLocalDataSource {

    override fun getPopularMovieList(): Observable<List<MovieVO>> {
        return daoPopular.getAllPopularMovieList().map {
            mapper.reverseMap(it)
        }
    }

    override fun getPopularMovieNormalList(): List<MovieVO> {
        return mapper.reverseMap(daoPopular.getAllPopularMovieNormalList())
    }

    override fun getPopularMovieDetailById(id: String): Observable<MovieVO> {
        return daoPopular.getPopularMovieDetailById(id).map {
            detailMapper.map(it)
        }
    }

    override fun savePopularMovieList(list: List<MovieVO>) {
        mapper.map(list).let {
            daoPopular.saveMovieList(it)
        }
    }

    override fun updateFavouriteMovieWithId(id: String, isFavourite: Boolean) {
        daoPopular.updateFavouriteWithId(id, isFavourite)
    }


}