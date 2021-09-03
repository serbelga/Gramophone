/*
 * Copyright (c) Gramophone 2019.
 */

package com.android.sergiobelda.gramophone.repository.lastfm

import com.android.sergiobelda.gramophone.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface LastFmWebservice {
    @GET("?method=artist.getinfo&format=json&api_key=") //+ BuildConfig.LASTFM_API_KEY)
    suspend fun getArtistInfo(@Query(value = "artist") name: String, @Query(value = "lang") lang: String): LastFmArtistResponse
}
