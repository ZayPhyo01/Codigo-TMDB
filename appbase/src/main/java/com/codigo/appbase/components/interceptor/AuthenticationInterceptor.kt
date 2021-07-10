package com.codigo.appbase.components.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException


class AuthenticationInterceptor() : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val originUrl = request.url()

        val appendUrl = originUrl.newBuilder()
            .addQueryParameter("api_key", "bf1cf4db88befb3e52de4d16dd3c2e10")
            .build()

        return chain.proceed(request.newBuilder().url(appendUrl).build())
    }

}