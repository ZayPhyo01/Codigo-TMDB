package com.codigo.tmdb.di

import com.codigo.tmdb.data.datasources.*
import com.codigo.tmdb.repositories.PopularMovieRepository
import com.codigo.tmdb.repositories.PopularMovieRepositoryImpl
import com.codigo.tmdb.repositories.UpComingMovieRepository
import com.codigo.tmdb.repositories.UpComingMovieRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MovieRepositoryModule {

    @Binds
    fun bindPopularMovieNetworkDataSource(dataSourceImplPopular: PopularMovieNetworkDataSourceImpl): PopularMovieNetworkDataSource

    @Binds
    fun bindUpcomingMovieNetworkDataSource(dataSourceImplUpComing: UpComingMovieNetworkDataSourceImpl): UpComingMovieNetworkDataSource

    @Binds
    fun bindPopularMovieRepository(repositoryImpl: PopularMovieRepositoryImpl): PopularMovieRepository

    @Binds
    fun bindUpComingMovieRepository(repositoryImpl: UpComingMovieRepositoryImpl): UpComingMovieRepository

    @Binds
    fun bindUpComingMovieLocalDataSource(upComingLocalDataSource: UpComingLocalDataSourceImpl): UpComingLocalDataSource

    @Binds
    fun bindPopularMovieLocalDataSource(popularMovieLocalDataSource: PopularMovieLocalDataSource): PopularMovieLocalDataSource


}