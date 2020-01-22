/*
 * Copyright (c) Gramophone 2020.
 */

package com.android.sergiobelda.gramophone.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.sergiobelda.gramophone.model.Track

/**
 * MainViewModel
 * @author Sergio Belda Galbis (@serbelga)
 */
class MainViewModel : ViewModel() {
    val track = MutableLiveData<Track>()

    fun select(track: Track) {
        this.track.value = track
    }
}