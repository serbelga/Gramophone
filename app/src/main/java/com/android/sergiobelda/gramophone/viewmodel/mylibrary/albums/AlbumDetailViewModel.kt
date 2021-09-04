/*
 * Copyright (c) Gramophone 2019.
 */

package com.android.sergiobelda.gramophone.viewmodel.mylibrary.albums

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.android.sergiobelda.gramophone.model.Album
import com.android.sergiobelda.gramophone.repository.contentresolver.IContentResolverRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumDetailViewModel @Inject constructor(
    private val contentResolverRepository: IContentResolverRepository
) : ViewModel() {

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
