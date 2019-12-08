package com.android.sergiobelda.gramophone.viewmodel.mylibrary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.sergiobelda.gramophone.data.theDarkSide
import com.android.sergiobelda.gramophone.data.theWall
import com.android.sergiobelda.gramophone.model.Album
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class AlbumsViewModel : ViewModel(), CoroutineScope {
    private val job = Job()
    private val albumsLiveData: MutableLiveData<ArrayList<Album>> by lazy {
        MutableLiveData<ArrayList<Album>>().also {
            loadUsers()
        }
    }

    fun getAlbums(): LiveData<ArrayList<Album>> {
        return albumsLiveData
    }

    private fun loadUsers() {
        launch(Dispatchers.Main) {
            val albums: ArrayList<Album> = withContext(Dispatchers.IO) { //async coroutine with .await()
                arrayListOf(theDarkSide, theWall)
            }
            albumsLiveData.value = albums
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}