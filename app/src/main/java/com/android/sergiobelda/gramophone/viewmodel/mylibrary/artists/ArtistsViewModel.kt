/*
 * Copyright (c) Gramophone 2019.
 */

package com.android.sergiobelda.gramophone.viewmodel.mylibrary.artists

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.android.sergiobelda.gramophone.repository.contentresolver.IContentResolverRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

@HiltViewModel
class ArtistsViewModel @Inject constructor(
    private val contentResolverRepository: IContentResolverRepository
) : ViewModel() {

    val artists = liveData(Dispatchers.IO) {
        val artists = contentResolverRepository.loadArtists()
        emit(artists)
    }
}
