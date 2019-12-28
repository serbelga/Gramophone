package com.android.sergiobelda.gramophone.viewmodel.mylibrary.albums

import android.app.Application
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.*
import com.android.sergiobelda.gramophone.model.Album
import com.android.sergiobelda.gramophone.model.Artist
import kotlinx.coroutines.*

class AlbumsViewModel(application: Application) : AndroidViewModel(application) {
    private val albumsLiveData: MutableLiveData<ArrayList<Album>> by lazy {
        MutableLiveData<ArrayList<Album>>().also {
            loadAlbums()
        }
    }

    fun getAlbums(): LiveData<ArrayList<Album>> {
        return albumsLiveData
    }

    private fun loadAlbums() {
        viewModelScope.launch {
            val albums: ArrayList<Album> = withContext(Dispatchers.IO) { // async coroutine with .await()
                val albumsList = arrayListOf<Album>()
                val contentResolver = getApplication<Application>().contentResolver
                val uri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI
                try {
                    val cursor = contentResolver.query(uri, null, null, null, null)
                    if (cursor != null && cursor.count > 0) {
                        while (cursor.moveToNext()) {
                            val albumId = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums._ID))
                            val album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM))
                            val artistId = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ARTIST_ID))
                            val albumCoverUri = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ALBUM_ART))
                            val artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Albums.ARTIST))
                            val albumReleaseYear = cursor.getShort(cursor.getColumnIndex(MediaStore.Audio.Albums.FIRST_YEAR))

                            albumsList.add(Album(albumId, album, arrayListOf(Artist(artistId, artist, null, null)), null, null, null, 0, albumCoverUri,
                                albumReleaseYear.toInt(),null, null, null))
                        }
                    }
                    cursor?.close()
                } catch (e : Exception) {
                    Log.e(TAG, e.message.toString())
                }
                albumsList
            }
            albumsLiveData.value = albums
        }
    }

    companion object {
        const val TAG = "AlbumsViewModel"
    }
}