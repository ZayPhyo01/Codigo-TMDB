package com.codigo.tmdb.components

import android.content.Context

import com.codigo.appbase.components.exception.NetworkException
import com.appbase.components.interfaces.NetworkExceptionMessageFactory
import com.codigo.appbase.components.impls.NetworkExceptionMessageFactoryImpl
import com.codigo.appbase.vos.ReturnResult
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.inject.Inject


class GenericErrorMessageFactoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val networkExceptionMessageFactory: NetworkExceptionMessageFactoryImpl
) : GenericErrorMessageFactory {

    override fun getErrorMessage(throwable: Throwable): CharSequence {
        return getErrorMessage(throwable, 0)
    }

    override fun getErrorMessage(throwable: Throwable, defaultText: Int): CharSequence {
        return when (throwable) {
            is UnknownHostException -> networkExceptionMessageFactory.getErrorMessage(throwable)
            is SocketTimeoutException -> networkExceptionMessageFactory.getErrorMessage(throwable)
            is ConnectException -> networkExceptionMessageFactory.getErrorMessage(throwable)
            is NetworkException -> networkExceptionMessageFactory.getErrorMessage(throwable)
            else -> {
                return try {
                    context.getString(defaultText)
                } catch (e: Exception) {
                    throwable.message ?:  "Fail to load"
                }
            }
        }
    }

    override fun <T> getError(throwable: Throwable): ReturnResult<T> {
        return getError(throwable, 0)
    }

    override fun <T> getError(throwable: Throwable, defaultText: Int): ReturnResult<T> {
        return when (throwable) {
            is UnknownHostException -> ReturnResult.NetworkErrorResult
            is SocketTimeoutException -> ReturnResult.NetworkErrorResult
            is ConnectException -> ReturnResult.NetworkErrorResult
            is NetworkException -> getNetworkError(throwable)
            else -> ReturnResult.ErrorResult(getErrorMessage(throwable, defaultText))
        }
    }

    override fun <T> getLoginError(throwable: Throwable, defaultText: Int): ReturnResult<T> {
        return when (throwable) {
            is UnknownHostException -> ReturnResult.NetworkErrorResult
            is SocketTimeoutException -> ReturnResult.NetworkErrorResult
            is ConnectException -> ReturnResult.NetworkErrorResult
            is NetworkException -> ReturnResult.ErrorResult(getErrorMessage(throwable))
            else -> ReturnResult.ErrorResult(getErrorMessage(throwable, defaultText))
        }
    }

    private fun <T> getNetworkError(networkException: NetworkException): ReturnResult<T> {
        return when (networkException.errorCode) {
            301 -> ReturnResult.NewVersion
            401 -> ReturnResult.SessionExpired
            422 -> ReturnResult.ValidationErrorResult(getErrorMessage(networkException))
            else -> ReturnResult.ErrorResult(getErrorMessage(networkException))
        }
    }

}