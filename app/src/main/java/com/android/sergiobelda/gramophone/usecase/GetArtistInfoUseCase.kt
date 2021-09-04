package com.android.sergiobelda.gramophone.usecase

import com.android.sergiobelda.gramophone.data.Result
import com.android.sergiobelda.gramophone.model.ArtistBio
import com.android.sergiobelda.gramophone.repository.artist.IArtistRepository

class GetArtistInfoUseCase(private val artistRepository: IArtistRepository) {

    suspend operator fun invoke(name: String, lang: String): Result<ArtistBio> =
        artistRepository.getArtistBio(name, lang)
}
