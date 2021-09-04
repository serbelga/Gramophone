package com.android.sergiobelda.gramophone.di

import com.android.sergiobelda.gramophone.localdatasource.ArtistLocalDataSource
import com.android.sergiobelda.gramophone.localdatasource.IArtistLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataSourceModule {

    @Singleton
    @Provides
    fun provideArtistLocalDataSource(): IArtistLocalDataSource =
        ArtistLocalDataSource()
}
