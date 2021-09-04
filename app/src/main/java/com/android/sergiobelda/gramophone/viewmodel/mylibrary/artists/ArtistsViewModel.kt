/*
 * Copyright (c) Gramophone 2019.
 */

package com.android.sergiobelda.gramophone.viewmodel.mylibrary.artists

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.liveData
import com.android.sergiobelda.gramophone.repository.contentresolver.ContentResolverRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class ArtistsViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {
    private val contentResolverRepository = ContentResolverRepository(getApplication())

    val artists = liveData(Dispatchers.IO) {
        val artists = contentResolverRepository.loadArtists()
        emit(artists)
    }
}
