/*
 * Copyright (c) Gramophone 2019-2020.
 */

package com.android.sergiobelda.gramophone.mediaplayer

import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.MediaSessionCompat
import androidx.core.app.NotificationManagerCompat
import androidx.media.MediaBrowserServiceCompat
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.audio.AudioAttributes
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

open class MusicService : MediaBrowserServiceCompat() {
    private lateinit var notificationManager: NotificationManagerCompat
    private lateinit var notificationBuilder: NotificationBuilder
    // private lateinit var mediaSource: MediaSource

    private val serviceJob = SupervisorJob()
    private val serviceScope = CoroutineScope(Dispatchers.Main + serviceJob)

    protected lateinit var mediaSession: MediaSessionCompat
    protected lateinit var mediaController: MediaControllerCompat
    protected lateinit var mediaSessionConnector: MediaSessionConnector

    private var isForegroundService = false

    private val mAudioAttributes = AudioAttributes.Builder()
        .setContentType(C.CONTENT_TYPE_MUSIC)
        .setUsage(C.USAGE_MEDIA)
        .build()

    private val exoPlayer: ExoPlayer by lazy {
        SimpleExoPlayer.Builder(this).build().apply {
            audioAttributes = mAudioAttributes
        }
    }


    override fun onCreate() {
        super.onCreate()

        val sessionActivityPendingIntent =
            packageManager?.getLaunchIntentForPackage(packageName)?.let { sessionIntent ->
                PendingIntent.getActivity(this, 0, sessionIntent, 0)
            }

        mediaSession = MediaSessionCompat(this, "MusicService")
            .apply {
                setSessionActivity(sessionActivityPendingIntent)
                isActive = true
            }

        sessionToken = mediaSession.sessionToken


        mediaController = MediaControllerCompat(this, mediaSession).also {
            // it.registerCallback(MediaControllerCallback())
        }

        notificationBuilder = NotificationBuilder(this)
        notificationManager = NotificationManagerCompat.from(this)
    }

    override fun onTaskRemoved(rootIntent: Intent?) {
        super.onTaskRemoved(rootIntent)

        exoPlayer.stop(true)
    }

    override fun onDestroy() {
        mediaSession.run {
            isActive = false
            release()
        }

        serviceJob.cancel()
    }

    override fun onLoadChildren(
        parentId: String,
        result: Result<MutableList<MediaBrowserCompat.MediaItem>>
    ) {

    }

    override fun onGetRoot(
        clientPackageName: String,
        clientUid: Int,
        rootHints: Bundle?
    ): BrowserRoot? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onSearch(
        query: String,
        extras: Bundle?,
        result: Result<MutableList<MediaBrowserCompat.MediaItem>>
    ) {
        super.onSearch(query, extras, result)
    }

    private fun removeNowPlayingNotification() {
        stopForeground(true)
    }
}