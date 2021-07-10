package com.codigo.tmdb.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.codigo.tmdb.components.GenericErrorMessageFactoryImpl
import com.codigo.appbase.viewmodels.BaseViewModel
import com.codigo.appbase.vos.ReturnResult
import com.codigo.tmdb.repositories.PopularMovieRepository
import com.codigo.tmdb.repositories.UpComingMovieRepository
import com.codigo.tmdb.repositories.datastate.PopularMovieListState
import com.codigo.tmdb.repositories.datastate.UpComingMovieListState
import com.codigo.tmdb.vos.MovieVO
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class HomeMovieViewModel @Inject constructor(
    private val repository: PopularMovieRepository,
    private val upComingMovieRepository: UpComingMovieRepository,
    private val genericErrorMessageFactoryImpl: GenericErrorMessageFactoryImpl

) : BaseViewModel() {

    private val popularListMutableLD = MutableLiveData<ReturnResult<List<MovieVO>>>()
    val popularlistLD: LiveData<ReturnResult<List<MovieVO>>> = popularListMutableLD

    private val upComingListMutableLD = MutableLiveData<ReturnResult<List<MovieVO>>>()
    val upComingListLD: LiveData<ReturnResult<List<MovieVO>>> = upComingListMutableLD

    init {
        loadPopularList()
        loadUpComingList()
    }

    private fun loadPopularList() {
        repository.getPopularList()

            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                popularListMutableLD.value = ReturnResult.Loading
            }
            .subscribe({
                when (it) {
                    is PopularMovieListState.Success -> {
                        popularListMutableLD.value = ReturnResult.PositiveResult(it.list)
                    }

                    is PopularMovieListState.Error -> {
                        popularListMutableLD.value =
                            genericErrorMessageFactoryImpl.getError(it.throwable)
                    }
                }
            }, {
                popularListMutableLD.value = genericErrorMessageFactoryImpl.getError(it)
            }).addToCompositeDisposable()
    }

    private fun loadUpComingList() {
        upComingMovieRepository.getUpComingMovieList()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                when (it) {
                    is UpComingMovieListState.Success -> {
                        upComingListMutableLD.value = ReturnResult.PositiveResult(it.list)
                    }
                    is UpComingMovieListState.Error -> {
                        upComingListMutableLD.value =
                            genericErrorMessageFactoryImpl.getError(it.throwable)
                    }
                    is UpComingMovieListState.Loading -> {
                        upComingListMutableLD.value = ReturnResult.Loading
                    }
                }
            }, {
                upComingListMutableLD.value = genericErrorMessageFactoryImpl.getError(it)
            }).addToCompositeDisposable()
    }



    fun onTapPopularMovieFavourite(id: String, isFavorite: Boolean) {
        repository.updateFavouriteMovieWithId(id, isFavorite)
    }

    fun onTapUpComingMovieFavourite(id: String, isFavorite: Boolean) {
        upComingMovieRepository.updateFavouriteMovieWithId(id, isFavorite)
    }
}