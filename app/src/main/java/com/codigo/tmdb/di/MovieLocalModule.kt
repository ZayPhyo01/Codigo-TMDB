package com.codigo.tmdb.di

import android.content.Context
import androidx.room.Room
import com.codigo.tmdb.data.local.MovieDataBase
import com.codigo.tmdb.data.local.dao.PopularMovieDao
import com.codigo.tmdb.data.local.dao.UpComingMovieDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MovieLocalModule {

    @Singleton
    @Provides
    fun providePopularMovieDao (movieDataBase: MovieDataBase) : PopularMovieDao{
        return movieDataBase.getPopularMovieDao()
    }

    @Singleton
    @Provides
    fun provideUpComingMovieDao (movieDataBase: MovieDataBase) : UpComingMovieDao{
        return movieDataBase.getUpComingMovieDao()
    }

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): MovieDataBase {
        return Room.databaseBuilder(
            context.applicationContext,
            MovieDataBase::class.java,
            "movie_offline_data_base.db"
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

}