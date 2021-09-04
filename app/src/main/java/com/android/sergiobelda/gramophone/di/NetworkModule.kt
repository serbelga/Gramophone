package com.android.sergiobelda.gramophone.di

import com.android.sergiobelda.gramophone.BuildConfig
import com.android.sergiobelda.gramophone.api.Constants
import com.android.sergiobelda.gramophone.api.lastfm.ILastFmApiClient
import com.android.sergiobelda.gramophone.api.lastfm.ILastFmApiService
import com.android.sergiobelda.gramophone.api.lastfm.LastFmApiClient
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideInterceptor(): Interceptor =
        Interceptor { chain ->
            val url = chain.request().url()
                .newBuilder()
                .addQueryParameter("api_key", BuildConfig.LASTFM_API_KEY)
                .build()
            val request = chain.request().newBuilder().url(url).build()
            chain.proceed(request)
        }

    @Singleton
    @Provides
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .readTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(Constants.TIMEOUT, TimeUnit.SECONDS)
            .build()

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://ws.audioscrobbler.com/2.0/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .client(client)
            .build()

    @Singleton
    @Provides
    fun provideLastFmService(retrofit: Retrofit): ILastFmApiService = retrofit.create(ILastFmApiService::class.java)

    @Singleton
    @Provides
    fun provideLastFmClient(lastFmApiService: ILastFmApiService): ILastFmApiClient = LastFmApiClient(lastFmApiService)
}
