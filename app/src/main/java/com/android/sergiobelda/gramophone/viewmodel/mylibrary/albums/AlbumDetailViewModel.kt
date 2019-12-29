package com.android.sergiobelda.gramophone.viewmodel.mylibrary.albums

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.android.sergiobelda.gramophone.model.Album
import com.android.sergiobelda.gramophone.repository.contentresolver.ContentResolverRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AlbumDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val contentResolverRepository = ContentResolverRepository(getApplication())

    val album = MutableLiveData<Album>()

    fun getAlbum(albumId: String) =
        viewModelScope.launch {
            album.value = contentResolverRepository.getAlbum(albumId)
        }

    fun getTracksByAlbumId(albumId: String) =
        liveData(Dispatchers.IO) {
            val tracks = contentResolverRepository.getTracksByAlbumId(albumId)
            emit(tracks)
        }

    companion object {
        const val TAG = "AlbumDetailViewModel"
    }
}