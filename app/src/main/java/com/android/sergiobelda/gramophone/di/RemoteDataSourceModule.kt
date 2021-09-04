package com.android.sergiobelda.gramophone.di

import com.android.sergiobelda.gramophone.api.lastfm.ILastFmApiClient
import com.android.sergiobelda.gramophone.remotedatasource.ArtistRemoteDataSource
import com.android.sergiobelda.gramophone.remotedatasource.IArtistRemoteDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

    @Singleton
    @Provides
    fun provideArtistRemoteDataSource(lastFmApiClient: ILastFmApiClient): IArtistRemoteDataSource =
        ArtistRemoteDataSource(lastFmApiClient)
}
