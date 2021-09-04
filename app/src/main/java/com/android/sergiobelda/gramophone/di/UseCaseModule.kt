package com.android.sergiobelda.gramophone.di

import com.android.sergiobelda.gramophone.repository.artist.IArtistRepository
import com.android.sergiobelda.gramophone.usecase.GetArtistInfoUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    @ViewModelScoped
    fun provideGetArtistInfoUseCase(artistRepository: IArtistRepository) = GetArtistInfoUseCase(artistRepository)
}
