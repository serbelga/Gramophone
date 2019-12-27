package com.android.sergiobelda.gramophone.repository.lastfm

class LastFmRepository {
    private var client: LastFmWebservice = LastFmRetrofitClient().lastFmWebservice

    suspend fun getArtistInfo(name: String, lang: String) = client.getArtistInfo(name, lang)
}