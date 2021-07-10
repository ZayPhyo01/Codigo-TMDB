package com.codigo.tmdb.data.datasources

import com.codigo.tmdb.data.local.dao.UpComingMovieDao
import com.codigo.tmdb.data.mappers.UpComingMovieDetailLocalMapper
import com.codigo.tmdb.data.mappers.UpComingMovieListLocalMapper
import com.codigo.tmdb.vos.MovieVO
import io.reactivex.Maybe
import io.reactivex.Observable
import javax.inject.Inject

class UpComingLocalDataSourceImpl @Inject constructor(
    private val daoUpComingMovieDao: UpComingMovieDao,
    private val mapper: UpComingMovieListLocalMapper,
    private val upComingMovieDetailLocalMapper: UpComingMovieDetailLocalMapper
) : UpComingLocalDataSource {

    override fun getUpComingMovieList(): Observable<List<MovieVO>> {
        return daoUpComingMovieDao.getAllUpComingMovieList().map {
            mapper.reverseMap(it)
        }
    }

    override fun getUpComingMovieDetailById(id: String): Observable<MovieVO> {
        return daoUpComingMovieDao.getUpComingMovieDetailById(id).map {
            upComingMovieDetailLocalMapper.map(it)
        }
    }


    override fun saveUpComingMovieList(list: List<MovieVO>) {
        mapper.map(list).let {
            daoUpComingMovieDao.saveMovieList(it)
        }
    }

    override fun updateFavouriteMovieWithId(id: String, isFavourite: Boolean) {
        daoUpComingMovieDao.updateFavouriteWithId(id, isFavourite)
    }
}