package com.codigo.tmdb.components

import androidx.annotation.StringRes
import com.codigo.appbase.vos.ReturnResult


interface GenericErrorMessageFactory {
    fun getErrorMessage(throwable: Throwable): CharSequence

    fun getErrorMessage(throwable: Throwable, @StringRes defaultText: Int): CharSequence

    fun <T> getError(throwable: Throwable): ReturnResult<T>

    fun <T> getError(throwable: Throwable, @StringRes defaultText: Int): ReturnResult<T>

    fun <T> getLoginError(throwable: Throwable, @StringRes defaultText: Int): ReturnResult<T>
}