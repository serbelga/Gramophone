package com.android.sergiobelda.gramophone.repository

class LastFmRepository {
    var client: LastFmWebservice = LastFmRetrofitClient().lastFmWebservice

    suspend fun getArtistInfo(name: String, lang: String) = client.getArtistInfo(name, lang)
}