/*
 * Copyright (c) Gramophone 2019.
 */

package com.android.sergiobelda.gramophone.viewmodel.mylibrary.albums

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import com.android.sergiobelda.gramophone.repository.contentresolver.ContentResolverRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class AlbumsViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {
    private val contentResolverRepository = ContentResolverRepository(getApplication())

    val albums = liveData(Dispatchers.IO) {
        val albums = contentResolverRepository.loadAlbums()
        emit(albums)
    }
}
