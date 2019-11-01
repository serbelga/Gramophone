package com.android.sergiobelda.gramophone.ui.mylibrary.pages


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager

import com.android.sergiobelda.gramophone.R
import com.android.sergiobelda.gramophone.entities.Album
import kotlinx.android.synthetic.main.fragment_songs.*

/**
 * A simple [Fragment] subclass.
 *
 */
class AlbumsFragment : Fragment() {
    lateinit var albumSelectedListener : AlbumSelectedListener

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_albums, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        val album = Album("The Dark Side Of The Moon", 1973, "LP", 1)
        val album1 = Album("The Wall", 1973, "LP", 1)
        val album2 = Album("The Wall", 1973, "LP", 1)
        val album3 = Album("The Wall", 1973, "LP", 1)
        val album4 = Album("The Wall", 1973, "LP", 1)
        val albums = arrayListOf(album, album1, album2, album3, album4)
        val adapter = AlbumsAdapter(albums)
        recyclerView.adapter = adapter
    }

    interface AlbumSelectedListener {
        fun onAlbumSelected(album: Album)
    }
}
