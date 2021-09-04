package com.android.sergiobelda.gramophone.api.lastfm

import com.android.sergiobelda.gramophone.api.model.LastFmArtistResponse
import retrofit2.Response

interface ILastFmApiClient {

    suspend fun getArtistInfo(name: String, lang: String): Response<LastFmArtistResponse>
}
