package com.android.sergiobelda.gramophone.viewmodel.mylibrary.artists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.sergiobelda.gramophone.data.*
import com.android.sergiobelda.gramophone.model.Artist
import kotlinx.coroutines.*

class ArtistsViewModel : ViewModel() {
    private val artistsLiveData: MutableLiveData<ArrayList<Artist>> by lazy {
        MutableLiveData<ArrayList<Artist>>().also {
            loadArtists()
        }
    }

    fun getArtists(): LiveData<ArrayList<Artist>> {
        return artistsLiveData
    }

    private fun loadArtists() {
        viewModelScope.launch {
            val artists = withContext(Dispatchers.IO) {
                arrayListOf(bobMarley, davidGilmour, ledZeppelin, makaya, milesDavis, pinkFloyd)
            }
            artistsLiveData.value = artists
        }
    }
}