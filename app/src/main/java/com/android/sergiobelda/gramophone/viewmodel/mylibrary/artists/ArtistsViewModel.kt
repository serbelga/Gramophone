package com.android.sergiobelda.gramophone.viewmodel.mylibrary.artists

import android.app.Application
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.*
import com.android.sergiobelda.gramophone.model.Artist
import kotlinx.coroutines.*

class ArtistsViewModel(application: Application) : AndroidViewModel(application) {
    private val artistsLiveData: MutableLiveData<ArrayList<Artist>> by lazy {
        MutableLiveData<ArrayList<Artist>>().also {
            loadArtists()
        }
    }

    fun getArtists(): LiveData<ArrayList<Artist>> {
        return artistsLiveData
    }

    private fun loadArtists() {
        viewModelScope.launch {
            val artists = withContext(Dispatchers.IO) {
                val artistsList = arrayListOf<Artist>()
                val contentResolver = getApplication<Application>().contentResolver
                val uri = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI
                val projection = arrayOf(
                    MediaStore.Audio.Artists.ARTIST_KEY,
                    MediaStore.Audio.Artists.ARTIST,
                    MediaStore.Audio.Artists.NUMBER_OF_ALBUMS
                )
                try {
                    val cursor = contentResolver.query(uri, null, null, null, null)
                    if (cursor != null && cursor.count > 0) {
                        while (cursor.moveToNext()) {
                            val artistId = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Artists._ID))
                            val artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Artists.ARTIST))
                            artistsList.add(Artist(artistId, artist, null, null))
                        }
                    }
                    cursor?.close()
                } catch (e : Exception) {
                    Log.e("MusicViewModel", e.message.toString())
                }
                artistsList
            }
            artistsLiveData.value = artists
        }
    }
}