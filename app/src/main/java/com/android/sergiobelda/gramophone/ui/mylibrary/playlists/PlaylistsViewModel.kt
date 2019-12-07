package com.android.sergiobelda.gramophone.ui.mylibrary.playlists

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class PlaylistsViewModel : ViewModel(), CoroutineScope {
    private val job = Job()



    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main
}