/*
 * Copyright (c) Gramophone 2019.
 */

package com.android.sergiobelda.gramophone.viewmodel.mylibrary.albums

import android.app.Application
import androidx.lifecycle.*
import com.android.sergiobelda.gramophone.repository.contentresolver.ContentResolverRepository
import kotlinx.coroutines.*

class AlbumsViewModel(application: Application) : AndroidViewModel(application) {
    private val contentResolverRepository = ContentResolverRepository(getApplication())

    val albums = liveData(Dispatchers.IO) {
        val albums = contentResolverRepository.loadAlbums()
        emit(albums)
    }
}