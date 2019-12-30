package com.android.sergiobelda.gramophone.model

data class Album(
    val id: String,
    val title: String,
    val artists: List<Artist>,
    val label: String?,
    val genres: List<Genre>?,
    val numDiscs: Int?,
    val numTracks: Int,
    val coverUri: String?,
    val releaseYear: Int,
    val releaseMonth: Int? = 1,
    val releaseDay: Int? = 1,
    val tracks: List<Track>?,
    val producer: String? = null,
    val copyright: String? = null,
    val format: String? = null
) {
    fun getArtists(): String {
        return artists.joinToString { it.name }
    }

    fun getNumTracks(): String {
        return if (numTracks > 1) {
            "$numTracks tracks"
        } else {
            "$numTracks track"
        }
    }
}