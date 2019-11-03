package com.android.sergiobelda.gramophone.model

import java.util.*

data class Album(
    val id: String,
    val title: String,
    val artists: List<Artist>,
    val label: String,
    val genres: List<Genre>,
    val numDiscs: Int,
    val coverUri: String,
    val releaseYear: Int,
    val releaseMonth: Int? = 1,
    val releaseDay: Int? = 1,
    val tracks: List<Track>,
    val producer: String? = null,
    val copyright: String? = null,
    val format: String? = null
)