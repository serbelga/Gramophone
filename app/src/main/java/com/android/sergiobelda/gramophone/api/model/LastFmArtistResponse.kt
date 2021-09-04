package com.android.sergiobelda.gramophone.api.model

data class LastFmArtistResponse(
    val artist: LastFmArtist?
)

data class LastFmArtist(
    val id: String,
    var name: String,
    val imageUri: String?,
    val bio: LastFmArtistBio?
)

data class LastFmArtistBio(
    val summary: String,
    val content: String
)
