package com.android.sergiobelda.gramophone.viewmodel.mylibrary.albums

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.sergiobelda.gramophone.data.theDarkSide
import com.android.sergiobelda.gramophone.data.theWall
import com.android.sergiobelda.gramophone.model.Album
import kotlinx.coroutines.*

class AlbumsViewModel : ViewModel() {
    private val albumsLiveData: MutableLiveData<ArrayList<Album>> by lazy {
        MutableLiveData<ArrayList<Album>>().also {
            loadUsers()
        }
    }

    fun getAlbums(): LiveData<ArrayList<Album>> {
        return albumsLiveData
    }

    private fun loadUsers() {
        viewModelScope.launch {
            val albums: ArrayList<Album> = withContext(Dispatchers.IO) { //async coroutine with .await()
                arrayListOf(theDarkSide, theWall)
            }
            albumsLiveData.value = albums
        }
    }
}