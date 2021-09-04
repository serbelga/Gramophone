/*
 * Copyright (c) Gramophone 2019.
 */

package com.android.sergiobelda.gramophone.model

data class Artist(
    val id: String,
    var name: String,
    val imageUri: String?,
    val bio: ArtistBio?
)
