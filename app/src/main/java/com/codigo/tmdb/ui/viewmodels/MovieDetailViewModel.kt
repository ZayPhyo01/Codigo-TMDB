package com.codigo.tmdb.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.codigo.appbase.viewmodels.BaseViewModel
import com.codigo.tmdb.repositories.PopularMovieRepository
import com.codigo.tmdb.repositories.UpComingMovieRepository
import com.codigo.tmdb.vos.MovieVO
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val popularMovieRepository: PopularMovieRepository,
    private val upComingMovieRepository: UpComingMovieRepository,
) : BaseViewModel() {

    private val movieDetailMutableLD: MutableLiveData<MovieVO> = MutableLiveData()
    val movieMovieDetailLD: LiveData<MovieVO> = movieDetailMutableLD

    fun loadPopularMovieDetailById(id: String) {
        popularMovieRepository.getPopularMovieDetailById(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                movieDetailMutableLD.value = it
            }.addToCompositeDisposable()
    }

    fun loadUpComingMovieDetailById(id: String) {
        upComingMovieRepository.getUpComingMovieDetailById(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe {
                movieDetailMutableLD.value = it
            }.addToCompositeDisposable()
    }

    fun onTapPopularMovieFavourite(id: String, isFavorite: Boolean) {
        popularMovieRepository.updateFavouriteMovieWithId(id, isFavorite)
    }

    fun onTapUpComingMovieFavourite(id: String, isFavorite: Boolean) {
        upComingMovieRepository.updateFavouriteMovieWithId(id, isFavorite)
    }
}