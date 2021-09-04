/*
 * Copyright (c) Gramophone 2019.
 */

package com.android.sergiobelda.gramophone.viewmodel.mylibrary.artists

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.sergiobelda.gramophone.model.Album
import com.android.sergiobelda.gramophone.repository.contentresolver.ContentResolverRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistDetailViewModel @Inject constructor(
    application: Application
) : AndroidViewModel(application) {

    private val contentResolverRepository = ContentResolverRepository(getApplication())

    val albums = MutableLiveData<ArrayList<Album>>()

    fun getAlbums(artistId: String) = viewModelScope.launch {
        albums.value = contentResolverRepository.getAlbums(artistId)
    }
}
