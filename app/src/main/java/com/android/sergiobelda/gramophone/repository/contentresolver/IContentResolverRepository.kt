package com.android.sergiobelda.gramophone.repository.contentresolver

import com.android.sergiobelda.gramophone.model.Album
import com.android.sergiobelda.gramophone.model.Artist
import com.android.sergiobelda.gramophone.model.Track

interface IContentResolverRepository {
    suspend fun getAlbum(albumId: String) : Album?

    suspend fun getTracksByAlbumId(albumId : String) : ArrayList<Track>

    suspend fun loadAlbums() : ArrayList<Album>

    suspend fun loadArtists() : ArrayList<Artist>
}