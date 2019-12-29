package com.android.sergiobelda.gramophone.viewmodel.mylibrary.artists

import android.app.Application
import android.provider.MediaStore
import android.util.Log
import androidx.lifecycle.*
import com.android.sergiobelda.gramophone.data.bobMarley
import com.android.sergiobelda.gramophone.model.Artist
import com.android.sergiobelda.gramophone.repository.contentresolver.ContentResolverRepository
import kotlinx.coroutines.*

class ArtistsViewModel(application: Application) : AndroidViewModel(application) {
    private val contentResolverRepository = ContentResolverRepository(getApplication())

    val artists = liveData(Dispatchers.IO) {
        val artists = contentResolverRepository.loadArtists()
        emit(artists)
    }
}