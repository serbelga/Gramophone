/*
 * Copyright (c) Gramophone 2019.
 */

package com.android.sergiobelda.gramophone.repository.contentresolver

import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.util.Size
import com.android.sergiobelda.gramophone.model.Album
import com.android.sergiobelda.gramophone.model.Artist
import com.android.sergiobelda.gramophone.model.Track

class ContentResolverRepository(val context: Context) : IContentResolverRepository {
    private var contentResolver: ContentResolver = context.contentResolver

    override suspend fun getAlbum(albumId: String): Album? {
        val uri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI
        val selection = "${MediaStore.Audio.Albums._ID} = $albumId"
        try {
            val query = contentResolver.query(uri, null, selection, null, null)
            query?.let { cursor ->
                return if (cursor.count > 0) {
                    cursor.moveToFirst()
                    val id = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Albums._ID))
                    val album =
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM))
                    val artistId =
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ARTIST_ID))
                    val albumCoverUri = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART))
                    /*
                    val albumUri = ContentUris.appendId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI.buildUpon(), id).build()
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        val albumCoverBitmap = contentResolver.loadThumbnail(albumUri, Size(640, 640), null)
                    }
                    */
                    val artist =
                        cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ARTIST))
                    val albumReleaseYear =
                        cursor.getShort(cursor.getColumnIndex(MediaStore.Audio.Albums.FIRST_YEAR))
                    val albumNumTracks =
                        cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Albums.NUMBER_OF_SONGS))
                    cursor.close()
                    Album(
                        id.toString(),
                        album,
                        arrayListOf(Artist(artistId, artist, null, null)),
                        null,
                        null,
                        null,
                        albumNumTracks,
                        albumCoverUri,
                        albumReleaseYear.toInt(),
                        null,
                        null,
                        null
                    )
                } else {
                    cursor.close()
                    null
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
        }
        return null
    }

    override suspend fun getTracksByAlbumId(albumId: String): ArrayList<Track> {
        val tracksList = arrayListOf<Track>()
        val uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
        val sortOrder = "${MediaStore.Audio.Media.TRACK} ASC"
        val selection =
            "${MediaStore.Audio.Media.ALBUM_ID} = $albumId AND ${MediaStore.Audio.Media.IS_MUSIC} != 0"
        try {
            val query = contentResolver.query(uri, null, selection, null, sortOrder)
            query?.let { cursor ->
                if (cursor.count > 0) {
                    while (cursor.moveToNext()) {
                        val id = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media._ID))
                        val title =
                            cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE))
                        val track =
                            cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TRACK))
                        val artistId =
                            cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST_ID))
                        val artist =
                            cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST))
                        val trackDuration = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION))
                        var trackNumber = 0
                        var cdNumber = 0
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
                            trackNumber = Integer.parseInt(track)
                            if (track.length == 4) {
                                trackNumber = Integer.parseInt(track.substring(1))
                                cdNumber = Character.getNumericValue(track.first())
                            }
                        } else {
                            track?.let {
                                val values = track.split("/")
                                trackNumber = values[0].toInt()
                            }
                        }
                        tracksList.add(
                            Track(
                                id,
                                title,
                                trackNumber,
                                arrayListOf(Artist(artistId, artist, null, null)),
                                null,
                                trackDuration,
                                emptyList(),
                                cdNumber,
                                null,
                                null
                            )
                        )
                    }
                }
                cursor.close()
            }
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
        }
        return tracksList
    }

    override suspend fun getAlbums(artistId: String): ArrayList<Album> {
        val albumsList = arrayListOf<Album>()
        val uri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI
        val selection = "${MediaStore.Audio.Albums.ARTIST_ID} = $artistId"
        try {
            val query = contentResolver.query(uri, null, selection, null, null)
            query?.let { cursor ->
                if (cursor.count > 0) {
                    while (cursor.moveToNext()) {
                        val albumId =
                            cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums._ID))
                        val album =
                            cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM))
                        val artistId =
                            cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ARTIST_ID))
                        // TODO Fix Album Art path null
                        val albumCoverUri =
                            cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART))
                        val artist =
                            cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ARTIST))
                        val albumReleaseYear =
                            cursor.getShort(cursor.getColumnIndex(MediaStore.Audio.Albums.FIRST_YEAR))

                        albumsList.add(
                            Album(
                                albumId,
                                album,
                                arrayListOf(Artist(artistId, artist, null, null)),
                                null,
                                null,
                                null,
                                0,
                                albumCoverUri,
                                albumReleaseYear.toInt(),
                                null,
                                null,
                                null
                            )
                        )
                    }
                }
                cursor.close()
            }
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
        }
        return albumsList
    }

    override suspend fun loadAlbums(): ArrayList<Album> {
        val albumsList = arrayListOf<Album>()
        val uri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI
        try {
            val query = contentResolver.query(uri, null, null, null, null)
            query?.let { cursor ->
                if (cursor.count > 0) {
                    while (cursor.moveToNext()) {
                        val albumId =
                            cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums._ID))
                        val album =
                            cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM))
                        val artistId =
                            cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ARTIST_ID))
                        val albumCoverUri =
                            cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART))
                        val artist =
                            cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ARTIST))
                        val albumReleaseYear =
                            cursor.getShort(cursor.getColumnIndex(MediaStore.Audio.Albums.FIRST_YEAR))

                        albumsList.add(
                            Album(
                                albumId,
                                album,
                                arrayListOf(Artist(artistId, artist, null, null)),
                                null,
                                null,
                                null,
                                0,
                                albumCoverUri,
                                albumReleaseYear.toInt(),
                                null,
                                null,
                                null
                            )
                        )
                    }
                }
                cursor.close()
            }
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
        }
        return albumsList
    }

    override suspend fun loadArtists(): ArrayList<Artist> {
        val artistsList = arrayListOf<Artist>()
        val uri = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI
        try {
            val query = contentResolver.query(uri, null, null, null, null)
            query?.let { cursor ->
                if (cursor.count > 0) {
                    while (cursor.moveToNext()) {
                        val artistId =
                            cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Artists._ID))
                        val artist =
                            cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Artists.ARTIST))
                        artistsList.add(Artist(artistId, artist, null, null))
                    }
                }
                cursor.close()
            }
        } catch (e: Exception) {
            Log.e(TAG, e.message.toString())
        }
        return artistsList
    }

    companion object {
        const val TAG = "ContentResolver"
    }
}