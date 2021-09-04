package com.android.sergiobelda.gramophone.di

import android.app.Application
import android.content.Context
import com.android.sergiobelda.gramophone.localdatasource.IArtistLocalDataSource
import com.android.sergiobelda.gramophone.remotedatasource.IArtistRemoteDataSource
import com.android.sergiobelda.gramophone.repository.artist.ArtistRepository
import com.android.sergiobelda.gramophone.repository.artist.IArtistRepository
import com.android.sergiobelda.gramophone.repository.contentresolver.ContentResolverRepository
import com.android.sergiobelda.gramophone.repository.contentresolver.IContentResolverRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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

    @Provides
    @Singleton
    fun provideContentResolverRepository(@ApplicationContext context: Context): IContentResolverRepository =
        ContentResolverRepository(context)
}
