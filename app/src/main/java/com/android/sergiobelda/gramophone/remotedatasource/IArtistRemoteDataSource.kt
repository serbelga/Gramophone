package com.android.sergiobelda.gramophone.remotedatasource

import com.android.sergiobelda.gramophone.data.Result
import com.android.sergiobelda.gramophone.model.ArtistBio

interface IArtistRemoteDataSource {

    suspend fun getArtistBio(name: String, lang: String): Result<ArtistBio>
}
