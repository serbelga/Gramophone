/*
 * Copyright (c) Gramophone 2019.
 */

package com.android.sergiobelda.gramophone.api.lastfm

import com.android.sergiobelda.gramophone.api.model.LastFmArtistResponse
import retrofit2.Response

class LastFmApiClient(private val lastFmApiService: ILastFmApiService) : ILastFmApiClient {

    override suspend fun getArtistInfo(name: String, lang: String): Response<LastFmArtistResponse> =
        lastFmApiService.getArtistInfo(name, lang)
}
