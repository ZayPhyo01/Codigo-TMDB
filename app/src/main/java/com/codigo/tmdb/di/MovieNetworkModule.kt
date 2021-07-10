package com.codigo.tmdb.di

import android.content.Context
import com.codigo.appbase.components.exception.NetworkExceptionInterceptor
import com.codigo.appbase.components.interceptor.AuthenticationInterceptor
import com.codigo.tmdb.constants.Constants
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Scope
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object MovieNetworkModule {

    @Provides
    @Singleton
    fun providesOkHttpClientBuilder(@ApplicationContext context: Context): OkHttpClient.Builder {
        return OkHttpClient.Builder().apply {

            addInterceptor(AuthenticationInterceptor())
                .addInterceptor(ChuckInterceptor(context).apply { showNotification(true) })
                .addInterceptor(NetworkExceptionInterceptor())
                .readTimeout(25, TimeUnit.SECONDS)
                .writeTimeout(25, TimeUnit.SECONDS)
                .connectTimeout(25, TimeUnit.SECONDS)
                .cache(null)
        }
    }


    @Provides
    @Singleton
    @Named("primary")
    fun providesPrimaryRetrofitBuilder(
        gson: Gson,
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
    }

    @Provides
    @Singleton
    fun gson(): Gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        .setLenient()
        .create()
}