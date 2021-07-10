package com.codigo.appbase.viewmodels

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


abstract class BaseViewModel(
) : ViewModel() {

    protected val compositeDisposable: CompositeDisposable = CompositeDisposable()
    protected val PROVIDER_FACEBOOK = "FACEBOOK"
    protected val PROVIDER_CREDENTIALS = "CREDENTAILS"

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }


    fun Disposable.addToCompositeDisposable(): Disposable = apply { compositeDisposable.add(this) }




}
