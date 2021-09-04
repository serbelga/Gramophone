package com.android.sergiobelda.gramophone.di

import com.android.sergiobelda.gramophone.localdatasource.IArtistLocalDataSource
import com.android.sergiobelda.gramophone.remotedatasource.IArtistRemoteDataSource
import com.android.sergiobelda.gramophone.repository.artist.ArtistRepository
import com.android.sergiobelda.gramophone.repository.artist.IArtistRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideArtistRepository(
        artistLocalDataSource: IArtistLocalDataSource,
        artistRemoteDataSource: IArtistRemoteDataSource
    ): IArtistRepository =
        ArtistRepository(artistLocalDataSource, artistRemoteDataSource)
}
