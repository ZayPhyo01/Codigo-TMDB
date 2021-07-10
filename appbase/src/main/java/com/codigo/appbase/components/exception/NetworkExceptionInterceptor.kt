package com.codigo.appbase.components.exception


import android.util.Log
import com.codigo.appbase.components.exception.NetworkException
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class NetworkExceptionInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        val response = chain.proceed(chain.request())
        Log.d("login response", response.toString())

        when (response.isSuccessful) {
            true -> return response
            false -> {
                throw NetworkException(response.body(), response.code())
            }
        }

    }
}