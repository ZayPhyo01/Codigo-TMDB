package com.codigo.tmdb.repositories


import com.codigo.tmdb.data.datasources.PopularMovieLocalDataSourceImpl
import com.codigo.tmdb.data.datasources.PopularMovieNetworkDataSource
import com.codigo.tmdb.repositories.datastate.PopularMovieListState
import com.codigo.tmdb.repositories.datastate.UpComingMovieListState
import com.codigo.tmdb.vos.MovieVO
import io.reactivex.Flowable

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.schedulers.Schedulers
import java.lang.RuntimeException
import java.util.concurrent.Flow
import javax.inject.Inject

class PopularMovieRepositoryImpl @Inject constructor(
    private val popularMovieNetworkDataSource: PopularMovieNetworkDataSource,
    private val popularMovieLocalDataSourceImpl: PopularMovieLocalDataSourceImpl
) :
    PopularMovieRepository {


    override fun getPopularList(): Observable<PopularMovieListState> {

        val handle =
            popularMovieNetworkDataSource.getPopularMovieList(1)
                .map<PopularMovieListState> { local ->

                    popularMovieLocalDataSourceImpl.savePopularMovieList(local)

                    PopularMovieListState.Success(local)

                }
                .onErrorResumeNext { throwable: Throwable ->
                    return@onErrorResumeNext ObservableSource {
                        it.onNext(PopularMovieListState.Error(throwable))
                    }
                }



        return Observable.merge(
            popularMovieLocalDataSourceImpl.getPopularMovieList().map<PopularMovieListState> {
                PopularMovieListState.Success(it)
            }, handle
        ).startWith {
            it.onNext(PopularMovieListState.Loading)
            it.onComplete()
        }


    }

    override fun getPopularMovieDetailById(id: String): Observable<MovieVO> {
        return popularMovieLocalDataSourceImpl.getPopularMovieDetailById(id)
    }

    override fun updateFavouriteMovieWithId(id: String, isFavourite: Boolean) {
        popularMovieLocalDataSourceImpl.updateFavouriteMovieWithId(id, isFavourite)
    }

}


