/*
 * Copyright (c) Gramophone 2019.
 */

package com.android.sergiobelda.gramophone.ui.mylibrary.playlists

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.sergiobelda.gramophone.R

/**
 * A simple [Fragment] subclass.
 */
class PlaylistsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_playlists, container, false)
    }
}
