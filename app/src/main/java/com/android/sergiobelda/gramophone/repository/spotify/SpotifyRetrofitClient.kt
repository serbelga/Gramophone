/*
 * Copyright (c) Gramophone 2019.
 */

package com.android.sergiobelda.gramophone.repository.spotify

import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SpotifyRetrofitClient {
    val spotifyWebservice: SpotifyWebservice by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.spotify.com/v1/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build().create(SpotifyWebservice::class.java)
    }
}