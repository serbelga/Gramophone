/*
 * Copyright (c) Gramophone 2019.
 */

package com.android.sergiobelda.gramophone.viewmodel.mylibrary.artists

import android.app.Application
import androidx.lifecycle.*
import com.android.sergiobelda.gramophone.repository.contentresolver.ContentResolverRepository
import kotlinx.coroutines.*

class ArtistsViewModel(application: Application) : AndroidViewModel(application) {
    private val contentResolverRepository = ContentResolverRepository(getApplication())

    val artists = liveData(Dispatchers.IO) {
        val artists = contentResolverRepository.loadArtists()
        emit(artists)
    }
}