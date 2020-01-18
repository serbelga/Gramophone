/*
 * Copyright (c) Gramophone 2019.
 */

package com.android.sergiobelda.gramophone.viewmodel.mylibrary.artists

import android.app.Application
import android.content.Context
import androidx.lifecycle.*
import com.android.sergiobelda.gramophone.model.Album
import com.android.sergiobelda.gramophone.model.ArtistBio
import com.android.sergiobelda.gramophone.repository.contentresolver.ContentResolverRepository
import com.android.sergiobelda.gramophone.repository.lastfm.LastFmRepository
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class ArtistDetailViewModel(application: Application) : AndroidViewModel(application) {

    private val lastFmRepository = LastFmRepository()

    private val contentResolverRepository = ContentResolverRepository(getApplication())

    val bio = MutableLiveData<ArtistBio>()

    val albums = MutableLiveData<ArrayList<Album>>()

    fun getArtistInfo(name: String) = viewModelScope.launch {
        val info = lastFmRepository.getArtistInfo(name, Locale.getDefault().language)
        if (info.artist != null) {
            bio.value = info.artist.bio
        } else {
            bio.value = ArtistBio("", "")
        }
    }

    fun getAlbums(artistId: String) = viewModelScope.launch {
        albums.value = contentResolverRepository.getAlbums(artistId)
    }
}