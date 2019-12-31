/*
 * Copyright (c) Gramophone 2019.
 */

package com.android.sergiobelda.gramophone.viewmodel.mylibrary.artists

import androidx.lifecycle.*
import com.android.sergiobelda.gramophone.model.ArtistBio
import com.android.sergiobelda.gramophone.repository.lastfm.LastFmRepository
import kotlinx.coroutines.launch
import java.util.*

class ArtistDetailViewModel : ViewModel() {
    private val lastFmRepository = LastFmRepository()

    val bio = MutableLiveData<ArtistBio>()

    fun getArtistInfo(name: String) = viewModelScope.launch {
        val info = lastFmRepository.getArtistInfo(name, Locale.getDefault().language)
        bio.value = info.artist.bio
    }
}