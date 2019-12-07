package com.android.sergiobelda.gramophone.model

data class Track(
    val id: String,
    val title: String,
    val number: Int,
    val artists: List<Artist>,
    val albumId: String,
    val duration_ms: Int,
    val composer: List<String> = emptyList(),
    val discNumber: Int,
    val genres: List<Genre>
) {
    fun getArtists() : String {
        return artists.joinToString { it.name }
    }
}
