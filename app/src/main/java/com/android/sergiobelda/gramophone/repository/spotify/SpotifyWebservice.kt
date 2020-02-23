/*
 * Copyright (c) Gramophone 2019.
 */

package com.android.sergiobelda.gramophone.repository.spotify

import retrofit2.http.GET
import retrofit2.http.Query

interface SpotifyWebservice {
    @GET("search")
    suspend fun search(@Query("q") name: String, @Query("type") type: String)
}