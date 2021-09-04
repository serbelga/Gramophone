/*
 * Copyright (c) Gramophone 2019.
 */

package com.android.sergiobelda.gramophone.viewmodel.mylibrary.albums

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.android.sergiobelda.gramophone.repository.contentresolver.IContentResolverRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(
    private val contentResolverRepository: IContentResolverRepository
) : ViewModel() {

    val albums = liveData(Dispatchers.IO) {
        val albums = contentResolverRepository.loadAlbums()
        emit(albums)
    }
}
