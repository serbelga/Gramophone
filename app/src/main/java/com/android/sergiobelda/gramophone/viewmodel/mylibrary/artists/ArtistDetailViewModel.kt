/*
 * Copyright (c) Gramophone 2019.
 */

package com.android.sergiobelda.gramophone.viewmodel.mylibrary.artists

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.sergiobelda.gramophone.model.Album
import com.android.sergiobelda.gramophone.repository.contentresolver.IContentResolverRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArtistDetailViewModel @Inject constructor(
    private val contentResolverRepository: IContentResolverRepository
) : ViewModel() {

    val albums = MutableLiveData<ArrayList<Album>>()

    fun getAlbums(artistId: String) = viewModelScope.launch {
        albums.value = contentResolverRepository.getAlbums(artistId)
    }
}
