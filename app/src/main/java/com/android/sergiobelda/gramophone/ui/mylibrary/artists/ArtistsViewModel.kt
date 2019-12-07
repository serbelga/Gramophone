package com.android.sergiobelda.gramophone.ui.mylibrary.artists

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.sergiobelda.gramophone.data.bobMarley
import com.android.sergiobelda.gramophone.data.ledZeppelin
import com.android.sergiobelda.gramophone.data.pinkFloyd
import com.android.sergiobelda.gramophone.model.Artist
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ArtistsViewModel : ViewModel(), CoroutineScope {
    private val job = Job()
    private val artistsLiveData: MutableLiveData<ArrayList<Artist>> by lazy {
        MutableLiveData<ArrayList<Artist>>().also {
            loadArtists()
        }
    }

    fun getArtists() : LiveData<ArrayList<Artist>> {
        return artistsLiveData
    }

    private fun loadArtists() {
        launch(Dispatchers.Main) {
            val artists = withContext(Dispatchers.IO) {
                arrayListOf(pinkFloyd, ledZeppelin, bobMarley)
            }
            artistsLiveData.value = artists
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}