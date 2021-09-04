package com.android.sergiobelda.gramophone.repository.artist

import com.android.sergiobelda.gramophone.data.Result
import com.android.sergiobelda.gramophone.model.ArtistBio

interface IArtistRepository {

    suspend fun getArtistBio(name: String, lang: String): Result<ArtistBio>
}
