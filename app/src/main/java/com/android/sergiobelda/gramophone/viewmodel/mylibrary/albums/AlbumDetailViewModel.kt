package com.android.sergiobelda.gramophone.viewmodel.mylibrary.albums

import android.app.Application
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.sergiobelda.gramophone.model.Album
import com.android.sergiobelda.gramophone.model.Artist
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlbumDetailViewModel(application: Application) : AndroidViewModel(application) {
    val album = MutableLiveData<Album>()

    fun getAlbum(albumId: String) {
        viewModelScope.launch {
            album.value = withContext(Dispatchers.IO) {
                val contentResolver = getApplication<Application>().contentResolver
                val uri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI
                val selection = "${MediaStore.Audio.Albums._ID} = $albumId"
                try {
                    val cursor = contentResolver.query(uri, null, selection, null, null)
                    if (cursor != null && cursor.count > 0) {
                        cursor.moveToFirst()
                        val id = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums._ID))
                        val album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM))
                        val artistId = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ARTIST_ID))
                        val albumCoverUri = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART))
                        val artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ARTIST))
                        val albumReleaseYear = cursor.getShort(cursor.getColumnIndex(MediaStore.Audio.Albums.FIRST_YEAR))
                        val albumNumTracks = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Albums.NUMBER_OF_SONGS))
                        cursor.close()
                        Album(id, album, arrayListOf(Artist(artistId, artist, null, null)), null, null, null, albumNumTracks, albumCoverUri, albumReleaseYear.toInt(),null, null, null)
                    } else {
                        cursor?.close()
                        null
                    }
                } catch (e : Exception) {
                    Log.e(TAG, e.message.toString())
                    null
                }
            }
        }
    }

    companion object {
        const val TAG = "AlbumDetailViewModel"
    }
}