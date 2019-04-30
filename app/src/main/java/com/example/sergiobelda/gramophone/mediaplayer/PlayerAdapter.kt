package com.example.sergiobelda.gramophone.mediaplayer

interface PlayerAdapter {
    fun loadMedia(resourceId: Int)

    fun release()

    fun isPlaying() : Boolean

    fun play()

    fun reset()

    fun pause()

    fun initializeProgressCallback()

    fun seekTo(position: Int)
}