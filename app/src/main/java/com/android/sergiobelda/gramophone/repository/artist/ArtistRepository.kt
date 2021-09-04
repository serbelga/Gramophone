/*
 * Copyright (c) Gramophone 2019.
 */

package com.android.sergiobelda.gramophone.repository.artist

import com.android.sergiobelda.gramophone.data.Result
import com.android.sergiobelda.gramophone.localdatasource.IArtistLocalDataSource
import com.android.sergiobelda.gramophone.model.ArtistBio
import com.android.sergiobelda.gramophone.remotedatasource.IArtistRemoteDataSource

class ArtistRepository(
    private val artistLocalDataSource: IArtistLocalDataSource,
    private val artistRemoteDataSource: IArtistRemoteDataSource
) : IArtistRepository {

    override suspend fun getArtistBio(name: String, lang: String): Result<ArtistBio> =
        artistRemoteDataSource.getArtistBio(name, lang)
}
