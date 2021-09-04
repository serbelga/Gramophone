/*
 * Copyright (c) Gramophone 2019.
 */

package com.android.sergiobelda.gramophone.api.lastfm

import com.android.sergiobelda.gramophone.api.model.LastFmArtistResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ILastFmApiService {

    @GET("?method=artist.getinfo&format=json")
    suspend fun getArtistInfo(
        @Query(value = "artist") name: String,
        @Query(value = "lang") lang: String
    ): Response<LastFmArtistResponse>
}
