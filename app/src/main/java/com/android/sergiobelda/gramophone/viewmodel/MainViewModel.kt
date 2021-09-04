/*
 * Copyright (c) Gramophone 2020.
 */

package com.android.sergiobelda.gramophone.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.sergiobelda.gramophone.data.Result
import com.android.sergiobelda.gramophone.model.ArtistBio
import com.android.sergiobelda.gramophone.model.Track
import com.android.sergiobelda.gramophone.usecase.GetArtistInfoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

/**
 * MainViewModel
 * @author Sergio Belda Galbis (@serbelga)
 */
@HiltViewModel
class MainViewModel @Inject constructor(
    private val getArtistInfoUseCase: GetArtistInfoUseCase
) : ViewModel() {
    val track = MutableLiveData<Track>()

    val bio = MutableLiveData<ArtistBio?>()

    fun select(track: Track) {
        this.track.value = track
    }

    fun getArtistInfo(name: String) = viewModelScope.launch {
        val info = getArtistInfoUseCase(name, Locale.getDefault().language)
        when (info) {
            is Result.Success -> bio.value = info.value
        }
    }
}
