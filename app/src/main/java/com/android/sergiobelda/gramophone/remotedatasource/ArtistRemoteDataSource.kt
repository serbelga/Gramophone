package com.android.sergiobelda.gramophone.remotedatasource

import com.android.sergiobelda.gramophone.api.lastfm.ILastFmApiClient
import com.android.sergiobelda.gramophone.api.mapper.toArtistBio
import com.android.sergiobelda.gramophone.api.safeApiCall
import com.android.sergiobelda.gramophone.data.Result
import com.android.sergiobelda.gramophone.model.ArtistBio
import kotlinx.coroutines.Dispatchers

class ArtistRemoteDataSource(private val lastFmApiClient: ILastFmApiClient) :
    IArtistRemoteDataSource {

    private val dispatcher = Dispatchers.IO

    override suspend fun getArtistBio(name: String, lang: String): Result<ArtistBio> =
        safeApiCall(dispatcher) {
            lastFmApiClient.getArtistInfo(name, lang)
        }.map { it.toArtistBio() }
}
