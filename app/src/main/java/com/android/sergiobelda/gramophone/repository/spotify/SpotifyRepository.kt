/*
 * Copyright (c) Gramophone 2019.
 */

package com.android.sergiobelda.gramophone.repository.spotify

class SpotifyRepository {
    private var client: SpotifyWebservice = SpotifyRetrofitClient().spotifyWebservice

    suspend fun getArtistInfo(name: String) = client.search(name, "artist")
}