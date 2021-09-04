package com.android.sergiobelda.gramophone.api.mapper

import com.android.sergiobelda.gramophone.api.model.LastFmArtistResponse
import com.android.sergiobelda.gramophone.model.ArtistBio

fun LastFmArtistResponse.toArtistBio() =
    ArtistBio(
        summary = artist?.bio?.summary ?: "",
        content = artist?.bio?.content ?: ""
    )
