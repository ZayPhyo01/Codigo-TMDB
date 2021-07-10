package com.codigo.tmdb.di

import com.codigo.tmdb.data.network.apis.MovieApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ApiServiceModule {

    @Singleton
    @Provides
    fun provideMovieService(@Named("authenticatedBuilder") retrofitBuilder: Retrofit.Builder)
            : MovieApi {
        return retrofitBuilder.build().create(MovieApi::class.java)
    }

    @Named("authenticatedBuilder")
    @Singleton
    @Provides
    fun getMovieRetrofitBuilder(
        httpClientBuilder: OkHttpClient.Builder,
        @Named("primary") retrofitBuilder: Retrofit.Builder
    ): Retrofit.Builder {
//            showLogD("NETWORK_MODULE", "TOKEN before interception : $token")
        return retrofitBuilder.client(httpClientBuilder.build())

    }

}