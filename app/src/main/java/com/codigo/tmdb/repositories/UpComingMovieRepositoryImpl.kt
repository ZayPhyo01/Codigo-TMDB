package com.codigo.tmdb.repositories

import com.codigo.tmdb.data.datasources.UpComingLocalDataSource
import com.codigo.tmdb.data.datasources.UpComingLocalDataSourceImpl
import com.codigo.tmdb.data.datasources.UpComingMovieNetworkDataSource
import com.codigo.tmdb.repositories.datastate.PopularMovieListState
import com.codigo.tmdb.repositories.datastate.UpComingMovieListState
import com.codigo.tmdb.vos.MovieVO
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UpComingMovieRepositoryImpl @Inject constructor(
    private val upComingMovieNetworkDataSource: UpComingMovieNetworkDataSource,
    private val upComingLocalDataSource: UpComingLocalDataSourceImpl
) :
    UpComingMovieRepository {

    override fun getUpComingMovieList(): Observable<UpComingMovieListState> {
        val handle = upComingMovieNetworkDataSource.getUpComingMovieList(1)

            .map<UpComingMovieListState> { local ->
                upComingLocalDataSource.saveUpComingMovieList(local)
                UpComingMovieListState.Success(local)
            }
            .startWith {
                it.onNext(UpComingMovieListState.Loading)
                it.onComplete()
            }
            .onErrorResumeNext { throwable: Throwable ->
                return@onErrorResumeNext ObservableSource {
                    it.onNext(UpComingMovieListState.Error(throwable))
                }
            }

        return Observable.merge(
            upComingLocalDataSource.getUpComingMovieList().map<UpComingMovieListState> {
                UpComingMovieListState.Success(it)
            },
            handle
        )


    }

    override fun updateFavouriteMovieWithId(id: String, isFavourite: Boolean) {
        upComingLocalDataSource.updateFavouriteMovieWithId(id, isFavourite)
    }

    override fun getUpComingMovieDetailById(id: String): Observable<MovieVO> {
        return upComingLocalDataSource.getUpComingMovieDetailById(id)
    }
}